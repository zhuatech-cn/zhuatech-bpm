/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bpm.controller;

import cn.zhuatech.bpm.common.ApiResponse;
import cn.zhuatech.bpm.service.ProcessSlaSimulationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bpm/insights")
public class ProcessSlaSimulationController {
    private final ProcessSlaSimulationService service;

    public ProcessSlaSimulationController(ProcessSlaSimulationService service) {
        this.service = service;
    }

    @PostMapping("/process-sla-simulation")
    public ApiResponse<ProcessSlaSimulationService.Result> simulate(
        @Valid @RequestBody ProcessSlaSimulationService.Request request) {
        return ApiResponse.ok(service.simulate(request));
    }
}
