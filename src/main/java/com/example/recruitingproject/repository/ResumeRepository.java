package com.example.recruitingproject.repository;

import com.example.recruitingproject.entity.Member;
import com.example.recruitingproject.entity.Resume;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findAllByMember(Member member);
}
