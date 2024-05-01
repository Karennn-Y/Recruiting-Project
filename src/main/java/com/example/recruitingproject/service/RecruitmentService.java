package com.example.recruitingproject.service;

import com.example.recruitingproject.dto.RecruitmentDTO;
import com.example.recruitingproject.entity.CompanyMember;
import com.example.recruitingproject.entity.Recruitment;
import com.example.recruitingproject.repository.CompanyMemberRepository;
import com.example.recruitingproject.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyMemberRepository companyMemberRepository;

    @Transactional
    public void postRecruitment(RecruitmentDTO.Request request) {
        // TODO: 해당 회원이 가입 회원인지 검증 필요
        CompanyMember companyMember = companyMemberRepository
                                            .findByLoginId(request.companyLoginId())
                                            .orElseThrow(() -> new RuntimeException("기업 회원 정보 없음!!!"));
        // TODO: 공고 등록
        Recruitment recruitment = request.toEntity();
        recruitment.setCompanyMember(companyMember);
        recruitment.setOpen();

        recruitmentRepository.save(recruitment);
    }
}
