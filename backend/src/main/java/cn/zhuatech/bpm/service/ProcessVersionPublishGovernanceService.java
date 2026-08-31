/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bpm.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessVersionPublishGovernanceService {
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.modelValidated()) blockers.add("流程模型校验未通过");
        if (!request.rolesResolved()) blockers.add("存在未绑定的角色或候选组");
        if (!request.segregationPassed()) blockers.add("审批节点存在职责冲突");
        if (!request.slaConfigured()) blockers.add("关键节点未配置 SLA 与升级策略");
        if (request.breakingChange() && request.activeInstances() > 0 && !request.migrationPlanReady()) blockers.add("破坏性变更缺少存量实例迁移方案");
        if (!request.rollbackPlanReady()) actions.add("补充版本回滚与恢复方案");
        if (!request.ownerApproved()) actions.add("取得流程所有者发布批准");

        Decision decision = !blockers.isEmpty() ? Decision.HOLD
                : !actions.isEmpty() ? Decision.APPROVAL_REQUIRED : Decision.PUBLISH;
        return new Assessment(request.processKey(), request.version(), decision,
                List.copyOf(blockers), List.copyOf(actions));
    }
    public record Request(@NotBlank String processKey, @Min(1) int version,
                          boolean modelValidated, boolean rolesResolved,
                          boolean segregationPassed, boolean slaConfigured,
                          boolean breakingChange, @Min(0) int activeInstances,
                          boolean migrationPlanReady, boolean rollbackPlanReady,
                          boolean ownerApproved) {}
    public record Assessment(String processKey, int version, Decision decision,
                             List<String> blockers, List<String> actions) {}
    public enum Decision { PUBLISH, APPROVAL_REQUIRED, HOLD }
}
