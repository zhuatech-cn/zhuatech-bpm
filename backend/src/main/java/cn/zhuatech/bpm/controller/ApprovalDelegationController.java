/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bpm.controller;

import cn.zhuatech.bpm.common.ApiResponse;
import cn.zhuatech.bpm.service.ApprovalDelegationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bpm/insights")
public class ApprovalDelegationController {
    private final ApprovalDelegationService service;
    public ApprovalDelegationController(ApprovalDelegationService service) { this.service = service; }

    @PostMapping("/approval-delegation")
    public ApiResponse<ApprovalDelegationService.Result> route(
        @Valid @RequestBody ApprovalDelegationService.Request request) {
        return ApiResponse.ok(service.route(request));
    }
}
