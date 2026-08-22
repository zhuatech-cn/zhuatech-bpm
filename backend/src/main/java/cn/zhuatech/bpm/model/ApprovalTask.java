/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bpm.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="bpm_approval_task")
public class ApprovalTask extends BaseEntity {
    @Column(nullable=false,unique=true,length=32) private String claimNo;
    @Column(nullable=false,length=40) private String claimant;
    @Column(nullable=false,length=60) private String department;
    @Column(nullable=false,length=40) private String category;
    @Column(nullable=false,length=160) private String purpose;
    @Column(nullable=false,precision=14,scale=2) private BigDecimal amount;
    @Column(nullable=false) private LocalDate expenseDate;
    @Column(nullable=false,length=20) private String status;

    protected ApprovalTask() {}
    public ApprovalTask(String no,String claimant,String department,String category,String purpose,BigDecimal amount,LocalDate date,String status){this.claimNo=no;this.claimant=claimant;this.department=department;this.category=category;this.purpose=purpose;this.amount=amount;this.expenseDate=date;this.status=status;}
    public String getClaimNo(){return claimNo;} public String getClaimant(){return claimant;} public String getDepartment(){return department;} public String getCategory(){return category;} public String getPurpose(){return purpose;} public BigDecimal getAmount(){return amount;} public LocalDate getExpenseDate(){return expenseDate;} public String getStatus(){return status;}
}
