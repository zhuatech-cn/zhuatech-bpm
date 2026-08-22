/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bpm.dto;

import cn.zhuatech.bpm.model.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public final class BpmDto {
    private BpmDto() {}
    public record AccountView(Long id,String accountCode,String accountName,String bankName,String accountType,BigDecimal balance,BigDecimal availableBalance,String currency,String status){public static AccountView from(ProcessDefinition x){return new AccountView(x.getId(),x.getAccountCode(),x.getAccountName(),x.getBankName(),x.getAccountType(),x.getBalance(),x.getAvailableBalance(),x.getCurrency(),x.getStatus());}}
    public record ProcessInstanceView(Long id,String processInstanceNo,String customerName,String sourceDocument,BigDecimal amount,BigDecimal receivedAmount,LocalDate dueDate,String owner,String status){public static ProcessInstanceView from(ProcessInstance x){return new ProcessInstanceView(x.getId(),x.getProcessInstanceNo(),x.getCustomerName(),x.getSourceDocument(),x.getAmount(),x.getReceivedAmount(),x.getDueDate(),x.getOwner(),x.getStatus());}}
    public record ProcessTaskView(Long id,String processTaskNo,String supplierName,String sourceDocument,BigDecimal amount,BigDecimal paidAmount,LocalDate dueDate,String applicant,String status){public static ProcessTaskView from(ProcessTask x){return new ProcessTaskView(x.getId(),x.getProcessTaskNo(),x.getSupplierName(),x.getSourceDocument(),x.getAmount(),x.getPaidAmount(),x.getDueDate(),x.getApplicant(),x.getStatus());}}
    public record ExpenseView(Long id,String claimNo,String claimant,String department,String category,String purpose,BigDecimal amount,LocalDate expenseDate,String status){public static ExpenseView from(ApprovalTask x){return new ExpenseView(x.getId(),x.getClaimNo(),x.getClaimant(),x.getDepartment(),x.getCategory(),x.getPurpose(),x.getAmount(),x.getExpenseDate(),x.getStatus());}}
    public record SlaPolicyView(Long id,String slaPolicyNo,String department,String subjectName,Integer fiscalYear,BigDecimal annualAmount,BigDecimal occupiedAmount,BigDecimal actualAmount,String status){public static SlaPolicyView from(SlaPolicy x){return new SlaPolicyView(x.getId(),x.getSlaPolicyNo(),x.getDepartment(),x.getSubjectName(),x.getFiscalYear(),x.getAnnualAmount(),x.getOccupiedAmount(),x.getActualAmount(),x.getStatus());}}
    public record Dashboard(BigDecimal cashBalance,BigDecimal availableCash,BigDecimal processInstanceAmount,BigDecimal overdueProcessInstance,BigDecimal processTaskAmount,BigDecimal expensePending,BigDecimal slaPolicyExecutionRate,List<ProcessInstanceView> upcomingProcessInstances,List<ProcessTaskView> upcomingProcessTasks){}
    public record CreateProcessInstanceRequest(@NotBlank String processInstanceNo,@NotBlank @Size(max=100) String customerName,@NotBlank String sourceDocument,@NotNull @Positive BigDecimal amount,@NotNull @FutureOrPresent LocalDate dueDate,@NotBlank String owner){}
    public record RecordReceiptRequest(@NotNull @Positive BigDecimal amount){}
    public record SubmitExpenseRequest(@NotBlank String claimant,@NotBlank String department,@NotBlank String category,@NotBlank @Size(max=160) String purpose,@NotNull @Positive BigDecimal amount,@NotNull @PastOrPresent LocalDate expenseDate){}
}
