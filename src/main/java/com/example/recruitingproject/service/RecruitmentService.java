package com.example.recruitingproject.service;

import com.example.recruitingproject.dto.RecruitmentDTO;
import com.example.recruitingproject.entity.CompanyMember;
import com.example.recruitingproject.entity.Recruitment;
import com.example.recruitingproject.enums.RecruitmentStatus;
import com.example.recruitingproject.repository.CompanyMemberRepository;
import com.example.recruitingproject.repository.RecruitmentRepository;
import java.util.List;
import java.util.Objects;
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

    @Transactional(readOnly = true) // 성능으로 인해 옵션 넣어주는것이 좋다 (훨신 가볍기 때문에)
    public List<RecruitmentDTO.Response> getRecruitmentList() {
        List<Recruitment> recruitments = recruitmentRepository.findAllByStatus(RecruitmentStatus.OPEN);
        return recruitments.stream().map(Recruitment::toDTO).toList();
    }

    @Transactional(readOnly = true) // 성능으로 인해 옵션 넣어주는것이 좋다 (훨신 가볍기 때문에)
    public RecruitmentDTO.Response getRecruitment(Long recruitmentId) {
        return recruitmentRepository.findById(recruitmentId)
                                    .orElseThrow(() -> new RuntimeException("해당 공고 없음!!!")).toDTO();
    }

    @Transactional
    public RecruitmentDTO.Response updateRecruitment(Long recruitmentId, RecruitmentDTO.Request request) {
        // TODO 해당 공고의 진짜 주인인지 조회 필요
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                                                        .orElseThrow(() -> new RuntimeException("해당 공고 없음!!!"));
        if (!Objects.equals(recruitment.getCompanyMember().getLoginId(), request.companyLoginId()))
            throw new RuntimeException("잘못된 기업 정보입니다!!!");
        // TODO 맞을 경우엔 업데이트 -> transactional update 처리
        return recruitment.update(request).toDTO();
    }

    @Transactional
    public void deleteRecruitment(Long recruitmentId,  RecruitmentDTO.Request request) {
        // TODO 해당 공고의 진짜 주인인지 조회 필요
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
            .orElseThrow(() -> new RuntimeException("해당 공고 없음!!!"));
        if (!Objects.equals(recruitment.getCompanyMember().getLoginId(), request.companyLoginId()))
            throw new RuntimeException("잘못된 기업 정보입니다!!!");
        // TODO 맞을 경우엔 삭제
        recruitmentRepository.deleteById(recruitmentId);
    }
}
