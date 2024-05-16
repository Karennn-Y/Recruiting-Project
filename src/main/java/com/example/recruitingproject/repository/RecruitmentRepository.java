package com.example.recruitingproject.repository;

import com.example.recruitingproject.entity.Recruitment;
import com.example.recruitingproject.enums.RecruitmentStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    List<Recruitment> findAllByStatus(RecruitmentStatus status);
    Optional<Recruitment> findByIdAndStatus(Long recruitmentId, RecruitmentStatus status);
}
