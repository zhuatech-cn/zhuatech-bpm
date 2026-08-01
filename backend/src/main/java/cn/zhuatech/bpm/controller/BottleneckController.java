/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bpm.controller;

import cn.zhuatech.bpm.common.ApiResponse;
import cn.zhuatech.bpm.service.BottleneckAssessmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bpm")
public class BottleneckController {
    private final BottleneckAssessmentService service;
    public BottleneckController(BottleneckAssessmentService service) { this.service = service; }

    @PostMapping("/bottleneck-assessment")
    public ApiResponse<BottleneckAssessmentService.Result> assess(@Valid @RequestBody BottleneckAssessmentService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
