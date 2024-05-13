package com.example.recruitingproject.repository;

import com.example.recruitingproject.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}
