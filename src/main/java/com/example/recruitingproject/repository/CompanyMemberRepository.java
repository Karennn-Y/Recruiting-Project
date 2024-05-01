package com.example.recruitingproject.repository;

import com.example.recruitingproject.entity.CompanyMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyMemberRepository extends JpaRepository<CompanyMember, Long> {

}
