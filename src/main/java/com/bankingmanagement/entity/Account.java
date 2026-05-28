package com.bankingmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_code_seq")
    @SequenceGenerator(name = "account_id_seq", sequenceName = "account_id_sequence", allocationSize = 1)
    @Column(name = "account_number")
    private Integer accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_balance")
    private Integer accountBalance;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "Branch_ID")
    private Branch branch;
}
