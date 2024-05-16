package com.example.recruitingproject.repository;

import com.example.recruitingproject.entity.Application;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByRecruitmentId(Long recruitmentId);
}
