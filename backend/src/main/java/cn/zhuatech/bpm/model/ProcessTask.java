/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bpm.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="bpm_process_task")
public class ProcessTask extends BaseEntity {
    @Column(nullable=false,unique=true,length=32) private String processTaskNo;
    @Column(nullable=false,length=100) private String supplierName;
    @Column(nullable=false,length=100) private String sourceDocument;
    @Column(nullable=false,precision=16,scale=2) private BigDecimal amount;
    @Column(nullable=false,precision=16,scale=2) private BigDecimal paidAmount;
    @Column(nullable=false) private LocalDate dueDate;
    @Column(nullable=false,length=40) private String applicant;
    @Column(nullable=false,length=20) private String status;

    protected ProcessTask() {}
    public ProcessTask(String no,String supplier,String source,BigDecimal amount,BigDecimal paid,LocalDate due,String applicant,String status){this.processTaskNo=no;this.supplierName=supplier;this.sourceDocument=source;this.amount=amount;this.paidAmount=paid;this.dueDate=due;this.applicant=applicant;this.status=status;}
    public String getProcessTaskNo(){return processTaskNo;} public String getSupplierName(){return supplierName;} public String getSourceDocument(){return sourceDocument;} public BigDecimal getAmount(){return amount;} public BigDecimal getPaidAmount(){return paidAmount;} public LocalDate getDueDate(){return dueDate;} public String getApplicant(){return applicant;} public String getStatus(){return status;}
}
