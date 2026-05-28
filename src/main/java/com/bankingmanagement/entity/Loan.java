package com.bankingmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_id_seq")
    @SequenceGenerator(name = "loan_id_seq", sequenceName = "loan_id_sequence", allocationSize = 1)
    @Column(name = "loan_id")
    private Integer loanId;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "loan_amount")
    private Integer loanAmount;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "Branch_ID")
    private Branch branch;


}
