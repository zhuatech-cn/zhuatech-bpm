/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bpm.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApprovalDelegationService {
    private static final int DAILY_CAPACITY_PER_APPROVER = 12;

    public record Request(
        @Min(0) int pendingTasks,
        @Min(0) int oldestTaskHours,
        @Min(1) int slaHours,
        @Min(0) int primaryApproversAvailable,
        @Min(0) int delegateApproversAvailable,
        @NotNull Boolean segregationConflict,
        @Min(0) int highValueTasks
    ) {}

    public record Result(String status, int primaryAssignments, int delegatedAssignments,
                         int escalatedAssignments, int estimatedClearanceHours, List<String> actions) {}

    public Result route(Request request) {
        int primaryCapacity = request.primaryApproversAvailable() * DAILY_CAPACITY_PER_APPROVER;
        int delegateCapacity = request.delegateApproversAvailable() * DAILY_CAPACITY_PER_APPROVER;
        int primaryAssignments = Math.min(request.pendingTasks(), primaryCapacity);
        int remaining = request.pendingTasks() - primaryAssignments;
        int delegated = Boolean.TRUE.equals(request.segregationConflict()) ? 0 : Math.min(remaining, delegateCapacity);
        int escalated = remaining - delegated;
        int totalApprovers = Math.max(1, request.primaryApproversAvailable() + request.delegateApproversAvailable());
        int clearanceHours = (int) Math.ceil(request.pendingTasks() * 8.0 / (totalApprovers * DAILY_CAPACITY_PER_APPROVER));

        List<String> actions = new ArrayList<>();
        String status;
        if (Boolean.TRUE.equals(request.segregationConflict()) || request.oldestTaskHours() >= request.slaHours()
            || escalated > 0) {
            status = "ESCALATE";
            actions.add("升级至流程负责人并重新分配审批池");
        } else if (delegated > 0 || request.oldestTaskHours() >= request.slaHours() * 0.75) {
            status = "ACTIVATE_DELEGATION";
            actions.add("启用无职责冲突的代理审批人");
        } else {
            status = "KEEP_PRIMARY";
            actions.add("保持主审批链并监控待办时长");
        }
        if (request.highValueTasks() > 0) actions.add("高金额事项保留双人复核和审计轨迹");
        if (Boolean.TRUE.equals(request.segregationConflict())) actions.add("阻止存在职责分离冲突的代理关系");
        return new Result(status, primaryAssignments, delegated, escalated, clearanceHours, List.copyOf(actions));
    }
}
