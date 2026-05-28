package com.bankingmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_sequence", allocationSize = 1)
    @Column(name = "cust_id")
    private Integer customerId;

    @Column(name = "cust_name")
    private String customerName;

    @Column(name = "cust_phone")
    private Integer customerPhone;

    @Column(name = "cust_address")
    private String customerAddress;

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;

    @OneToMany(mappedBy = "customer")
    private Set<Loan> loans;

}
