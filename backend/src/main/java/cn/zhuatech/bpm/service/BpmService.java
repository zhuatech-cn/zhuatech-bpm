/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bpm.service;

import cn.zhuatech.bpm.common.BusinessException;
import cn.zhuatech.bpm.dto.BpmDto.*;
import cn.zhuatech.bpm.model.*;
import cn.zhuatech.bpm.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(readOnly=true)
public class BpmService {
    private final ProcessDefinitionRepository accounts; private final ProcessInstanceRepository processInstances; private final ProcessTaskRepository processTasks; private final ApprovalTaskRepository expenses; private final SlaPolicyRepository slaPolicys;
    public BpmService(ProcessDefinitionRepository accounts,ProcessInstanceRepository processInstances,ProcessTaskRepository processTasks,ApprovalTaskRepository expenses,SlaPolicyRepository slaPolicys){this.accounts=accounts;this.processInstances=processInstances;this.processTasks=processTasks;this.expenses=expenses;this.slaPolicys=slaPolicys;}

    public Dashboard dashboard(){
        var accountList=accounts.findAll(); var processInstanceList=processInstances.findAllByOrderByDueDateAsc(); var processTaskList=processTasks.findAllByOrderByDueDateAsc(); var expenseList=expenses.findAllByOrderByExpenseDateDesc(); var slaPolicyList=slaPolicys.findByFiscalYearOrderByDepartmentAsc(LocalDate.now().getYear());
        BigDecimal cash=sum(accountList.stream().map(ProcessDefinition::getBalance).toList());
        BigDecimal available=sum(accountList.stream().map(ProcessDefinition::getAvailableBalance).toList());
        BigDecimal ar=sum(processInstanceList.stream().map(x->x.getAmount().subtract(x.getReceivedAmount())).toList());
        BigDecimal overdue=sum(processInstanceList.stream().filter(x->x.getDueDate().isBefore(LocalDate.now())&&!"已结清".equals(x.getStatus())).map(x->x.getAmount().subtract(x.getReceivedAmount())).toList());
        BigDecimal ap=sum(processTaskList.stream().map(x->x.getAmount().subtract(x.getPaidAmount())).toList());
        BigDecimal pending=sum(expenseList.stream().filter(x->"待审批".equals(x.getStatus())||"待付款".equals(x.getStatus())).map(ApprovalTask::getAmount).toList());
        BigDecimal annual=sum(slaPolicyList.stream().map(SlaPolicy::getAnnualAmount).toList()); BigDecimal actual=sum(slaPolicyList.stream().map(SlaPolicy::getActualAmount).toList());
        BigDecimal rate=annual.signum()==0?BigDecimal.ZERO:actual.multiply(new BigDecimal("100")).divide(annual,1,RoundingMode.HALF_UP);
        return new Dashboard(cash,available,ar,overdue,ap,pending,rate,processInstanceList.stream().limit(6).map(ProcessInstanceView::from).toList(),processTaskList.stream().limit(6).map(ProcessTaskView::from).toList());
    }
    private BigDecimal sum(List<BigDecimal> values){return values.stream().reduce(BigDecimal.ZERO,BigDecimal::add);}
    public List<AccountView> accounts(){return accounts.findAll().stream().map(AccountView::from).toList();}
    public List<ProcessInstanceView> processInstances(){return processInstances.findAllByOrderByDueDateAsc().stream().map(ProcessInstanceView::from).toList();}
    public List<ProcessTaskView> processTasks(){return processTasks.findAllByOrderByDueDateAsc().stream().map(ProcessTaskView::from).toList();}
    public List<ExpenseView> expenses(){return expenses.findAllByOrderByExpenseDateDesc().stream().map(ExpenseView::from).toList();}
    public List<SlaPolicyView> slaPolicys(){return slaPolicys.findByFiscalYearOrderByDepartmentAsc(LocalDate.now().getYear()).stream().map(SlaPolicyView::from).toList();}
    @Transactional public ProcessInstanceView createProcessInstance(CreateProcessInstanceRequest request){if(processInstances.findByProcessInstanceNo(request.processInstanceNo()).isPresent())throw new BusinessException("应收单号已存在");return ProcessInstanceView.from(processInstances.save(new ProcessInstance(request.processInstanceNo(),request.customerName(),request.sourceDocument(),request.amount(),BigDecimal.ZERO,request.dueDate(),request.owner(),"待收款")));}
    @Transactional public ProcessInstanceView recordReceipt(Long id,RecordReceiptRequest request){var item=processInstances.findById(id).orElseThrow(()->new BusinessException("应收记录不存在"));if("已结清".equals(item.getStatus()))throw new BusinessException("该应收已结清");if(item.getReceivedAmount().add(request.amount()).compareTo(item.getAmount())>0)throw new BusinessException("收款金额不能超过剩余应收");item.recordReceipt(request.amount());return ProcessInstanceView.from(item);}
    @Transactional public ExpenseView submitExpense(SubmitExpenseRequest request){String no="BX-"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));return ExpenseView.from(expenses.save(new ApprovalTask(no,request.claimant(),request.department(),request.category(),request.purpose(),request.amount(),request.expenseDate(),"待审批")));}
}
