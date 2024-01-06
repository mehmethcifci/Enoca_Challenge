package com.enoca.challenge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "company")
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long oid;
    @Column(name = "company_name")
    private String companyName;
    private String phone;
    private String address;
    private String email;
    @Column(name = "founding_year")
    private int foundingYear;
    private Long updatedAt;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employeeList;

}
