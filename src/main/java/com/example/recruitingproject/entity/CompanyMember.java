package com.example.recruitingproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "company_member_id")
    private Long id;
    private String companyName;
    private String loginId;
}
