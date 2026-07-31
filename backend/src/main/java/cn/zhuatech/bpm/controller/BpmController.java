/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bpm.controller;

import cn.zhuatech.bpm.common.ApiResponse;import cn.zhuatech.bpm.dto.BpmDto.*;import cn.zhuatech.bpm.service.BpmService;import jakarta.validation.Valid;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;import java.util.List;

@RestController @RequestMapping("/api/bpm")
public class BpmController {
    private final BpmService service; public BpmController(BpmService service){this.service=service;}
    @GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.dashboard());}
    @GetMapping("/process-definitions") public ApiResponse<List<AccountView>> accounts(){return ApiResponse.ok(service.accounts());}
    @GetMapping("/process-instances") public ApiResponse<List<ProcessInstanceView>> processInstances(){return ApiResponse.ok(service.processInstances());}
    @GetMapping("/process-tasks") public ApiResponse<List<ProcessTaskView>> processTasks(){return ApiResponse.ok(service.processTasks());}
    @GetMapping("/approval-tasks") public ApiResponse<List<ExpenseView>> expenses(){return ApiResponse.ok(service.expenses());}
    @GetMapping("/sla-policies") public ApiResponse<List<SlaPolicyView>> slaPolicys(){return ApiResponse.ok(service.slaPolicys());}
    @PostMapping("/process-instances") @PreAuthorize("hasAnyRole('ADMIN','PROCESS_MANAGER')") public ApiResponse<ProcessInstanceView> createProcessInstance(@Valid @RequestBody CreateProcessInstanceRequest request){return ApiResponse.ok("流程实例创建成功",service.createProcessInstance(request));}
    @PatchMapping("/process-instances/{id}/progress") @PreAuthorize("hasAnyRole('ADMIN','PROCESS_MANAGER')") public ApiResponse<ProcessInstanceView> recordReceipt(@PathVariable Long id,@Valid @RequestBody RecordReceiptRequest request){return ApiResponse.ok("流程进度更新成功",service.recordReceipt(id,request));}
    @PostMapping("/approval-tasks") @PreAuthorize("hasAnyRole('ADMIN','PROCESS_MANAGER','EMPLOYEE')") public ApiResponse<ExpenseView> submitExpense(@Valid @RequestBody SubmitExpenseRequest request){return ApiResponse.ok("流程任务已提交",service.submitExpense(request));}
}
