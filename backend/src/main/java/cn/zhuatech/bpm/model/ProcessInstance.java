/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.bpm.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="bpm_process_instance")
public class ProcessInstance extends BaseEntity {
    @Column(nullable=false,unique=true,length=32) private String processInstanceNo;
    @Column(nullable=false,length=100) private String customerName;
    @Column(nullable=false,length=100) private String sourceDocument;
    @Column(nullable=false,precision=16,scale=2) private BigDecimal amount;
    @Column(nullable=false,precision=16,scale=2) private BigDecimal receivedAmount;
    @Column(nullable=false) private LocalDate dueDate;
    @Column(nullable=false,length=40) private String owner;
    @Column(nullable=false,length=20) private String status;

    protected ProcessInstance() {}
    public ProcessInstance(String no,String customer,String source,BigDecimal amount,BigDecimal received,LocalDate due,String owner,String status){this.processInstanceNo=no;this.customerName=customer;this.sourceDocument=source;this.amount=amount;this.receivedAmount=received;this.dueDate=due;this.owner=owner;this.status=status;}
    public String getProcessInstanceNo(){return processInstanceNo;} public String getCustomerName(){return customerName;} public String getSourceDocument(){return sourceDocument;} public BigDecimal getAmount(){return amount;} public BigDecimal getReceivedAmount(){return receivedAmount;} public LocalDate getDueDate(){return dueDate;} public String getOwner(){return owner;} public String getStatus(){return status;}
    public void recordReceipt(BigDecimal value){this.receivedAmount=this.receivedAmount.add(value);this.status=this.receivedAmount.compareTo(this.amount)>=0?"已结清":"部分收款";}
}
