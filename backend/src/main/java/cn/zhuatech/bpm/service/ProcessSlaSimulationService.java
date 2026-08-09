/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bpm.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessSlaSimulationService {
    public Result simulate(Request request) {
        double workloadMinutes = request.remainingSteps() * request.averageStepMinutes()
            * (1 + request.reworkRate());
        int projectedMinutes = (int) Math.ceil(workloadMinutes / request.parallelWorkers());
        int bufferMinutes = request.slaRemainingMinutes() - projectedMinutes;
        int requiredParallelWorkers = Math.max(1,
            (int) Math.ceil(workloadMinutes / request.slaRemainingMinutes()));
        String decision = bufferMinutes < 0 ? "SLA_MISS"
            : bufferMinutes < Math.ceil(request.slaRemainingMinutes() * .20) ? "AT_RISK" : "ON_TRACK";

        List<String> actions = new ArrayList<>();
        if (requiredParallelWorkers > request.parallelWorkers()) {
            actions.add("将并行处理人数提升至 " + requiredParallelWorkers + " 人");
        }
        if (request.reworkRate() >= .15) actions.add("复核退回原因并在高频节点增加表单校验");
        if ("ON_TRACK".equals(decision)) actions.add("保持当前资源配置并监控最长等待节点");
        return new Result(request.processKey(), projectedMinutes, bufferMinutes,
            requiredParallelWorkers, decision, actions);
    }

    public record Request(@NotBlank String processKey, @Min(0) int completedSteps,
                          @Min(1) int remainingSteps, @Min(1) int averageStepMinutes,
                          @Min(1) int parallelWorkers, @Min(1) int slaRemainingMinutes,
                          @DecimalMin("0") @DecimalMax("1") double reworkRate) {}

    public record Result(String processKey, int projectedMinutes, int bufferMinutes,
                         int requiredParallelWorkers, String decision, List<String> actions) {}
}
