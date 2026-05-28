package com.bankingmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_id_seq")
    @SequenceGenerator(name = "branch_id_seq", sequenceName = "branch_id_sequence", allocationSize = 1)
    @Column(name = "branch_id")
    private Integer branchId;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "branch_address")
    private String branchAddress;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_code", referencedColumnName = "bank_code", nullable = false)
    private Bank bank;

    @OneToMany(mappedBy = "branch")
    private Set<Account> accountSet;

    @OneToMany(mappedBy = "branch")
    private Set<Loan> loanSet;

}
