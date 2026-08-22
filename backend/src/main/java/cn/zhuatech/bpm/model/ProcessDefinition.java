/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.bpm.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bpm_process_definition")
public class ProcessDefinition extends BaseEntity {
    @Column(nullable=false, unique=true, length=32) private String accountCode;
    @Column(nullable=false, length=80) private String accountName;
    @Column(nullable=false, length=80) private String bankName;
    @Column(nullable=false, length=40) private String accountType;
    @Column(nullable=false, precision=16, scale=2) private BigDecimal balance;
    @Column(nullable=false, precision=16, scale=2) private BigDecimal availableBalance;
    @Column(nullable=false, length=16) private String currency;
    @Column(nullable=false, length=20) private String status;

    protected ProcessDefinition() {}
    public ProcessDefinition(String code,String name,String bank,String type,BigDecimal balance,BigDecimal available,String currency,String status){this.accountCode=code;this.accountName=name;this.bankName=bank;this.accountType=type;this.balance=balance;this.availableBalance=available;this.currency=currency;this.status=status;}
    public String getAccountCode(){return accountCode;} public String getAccountName(){return accountName;} public String getBankName(){return bankName;} public String getAccountType(){return accountType;} public BigDecimal getBalance(){return balance;} public BigDecimal getAvailableBalance(){return availableBalance;} public String getCurrency(){return currency;} public String getStatus(){return status;}
}
