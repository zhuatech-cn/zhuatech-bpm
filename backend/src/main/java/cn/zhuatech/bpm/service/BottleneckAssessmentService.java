/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bpm.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BottleneckAssessmentService {
    public Result assess(Request request) {
        double waitingRatio = request.activeInstances() == 0 ? 0 : (double) request.waitingTasks() / request.activeInstances();
        int score = Math.min(100, (int) Math.round(request.averageTaskHours() / request.slaHours() * 40
            + Math.min(1, waitingRatio) * 30 + request.reworkRate() * 30));
        String level = score >= 75 ? "CRITICAL" : score >= 50 ? "HIGH" : score >= 25 ? "WATCH" : "NORMAL";
        List<String> actions = new ArrayList<>();
        if (request.averageTaskHours() > request.slaHours()) actions.add("拆分耗时节点并设置升级时限");
        if (waitingRatio >= .5) actions.add("增加节点处理人或启用代理规则");
        if (request.reworkRate() >= .2) actions.add("复核表单校验和退回原因");
        if (actions.isEmpty()) actions.add("保持当前流程资源配置");
        return new Result(request.processName(), score, level,
            Math.max(0, request.averageTaskHours() - request.slaHours()), actions);
    }

    public record Request(@NotBlank String processName, @Min(0) double averageTaskHours,
                          @Positive double slaHours, @Min(0) int activeInstances,
                          @Min(0) int waitingTasks,
                          @DecimalMin("0") @DecimalMax("1") double reworkRate) {}
    public record Result(String processName, int bottleneckScore, String level,
                         double predictedDelayHours, List<String> actions) {}
}
