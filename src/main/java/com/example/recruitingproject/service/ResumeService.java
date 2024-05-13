package com.example.recruitingproject.service;

import com.example.recruitingproject.dto.ResumeDTO;
import com.example.recruitingproject.entity.Member;
import com.example.recruitingproject.entity.Resume;
import com.example.recruitingproject.repository.MemberRepository;
import com.example.recruitingproject.repository.ResumeRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void postingResume (ResumeDTO.Request request) {
        // TODO: 해당 회원이 가입 회원이지 검증 필요
        Member member = memberRepository.findByLoginId(request.loginId())
                                        .orElseThrow(() -> new RuntimeException("가입된 회원 정보 없음!!!"));
        // TODO: 이력서 등록
        Resume resume = request.toEntity();
        resume.setMember(member);
        resume.setOpen();
        resumeRepository.save(resume);
    }
}
