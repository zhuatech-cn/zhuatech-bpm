/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bpm.controller;
import cn.zhuatech.bpm.common.ApiResponse;
import cn.zhuatech.bpm.service.ProcessVersionPublishGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/enterprise/bpm")
public class ProcessVersionPublishGovernanceController {
    private final ProcessVersionPublishGovernanceService service;
    public ProcessVersionPublishGovernanceController(ProcessVersionPublishGovernanceService service) { this.service = service; }
    @PostMapping("/process-version-publish")
    public ApiResponse<ProcessVersionPublishGovernanceService.Assessment> assess(@Valid @RequestBody ProcessVersionPublishGovernanceService.Request request) {
        return ApiResponse.ok("流程版本发布评估完成", service.assess(request));
    }
}
