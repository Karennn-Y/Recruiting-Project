package com.example.recruitingproject.service;

import com.example.recruitingproject.dto.ResumeDTO;
import com.example.recruitingproject.entity.Member;
import com.example.recruitingproject.entity.Resume;
import com.example.recruitingproject.repository.MemberRepository;
import com.example.recruitingproject.repository.ResumeRepository;
import java.util.List;
import java.util.Objects;
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

    @Transactional(readOnly = true)
    public List<ResumeDTO.Response> getResumeList (String loginId) {
        Member member = memberRepository.findByLoginId(loginId)
                                        .orElseThrow(() -> new RuntimeException("가입된 회원 정보 없음!!!"));
        List<Resume> resumes = resumeRepository.findAllByMember(member);
        return resumes.stream().map(Resume::toDTO).toList();
    }

    // TODO : 추후 개발 방향에 따라 로직 변경 필수
    @Transactional(readOnly = true)
    public ResumeDTO.Response getResume(Long resumeId, String loginId) {
        Member member = memberRepository.findByLoginId(loginId)
                                        .orElseThrow(() -> new RuntimeException("가입된 회원 정보 없음!!!"));
        Resume resume = resumeRepository.findByIdAndMember(resumeId, member)
                                        .orElseThrow(() -> new RuntimeException("회원정보 일치하지 않습니다 / 이력서 없음!!"));

        return resume.toDTO();
    }

    @Transactional
    public ResumeDTO.Response updateResume(Long resumeId, ResumeDTO.Request request) {
        Resume resume = resumeRepository.findById(resumeId)
                                        .orElseThrow(() -> new RuntimeException("해당 이력서 없음!!"));
        if (!Objects.equals(resume.getMember().getLoginId(), request.loginId())) {
            throw new RuntimeException("잘못된 로그인 아이디 입니다!");
        }
        return resume.update(request).toDTO();
    }
}
