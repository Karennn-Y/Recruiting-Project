package com.example.recruitingproject.repository;

import com.example.recruitingproject.entity.Member;
import com.example.recruitingproject.entity.Resume;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findAllByMember(Member member);
    // 이력서번호와 회원 번호로 조회하기
    Optional<Resume> findByIdAndMemberId(Long resumeId, Long memberId);
}
