package com.example.recruitingproject.entity;

import com.example.recruitingproject.dto.RecruitmentDTO;
import com.example.recruitingproject.enums.RecruitmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "recuitment_id")
    private Long id;
    private String title;
    private Integer recruiterCount;
    private LocalDateTime closingDate;

    @Enumerated(EnumType.STRING)
    private RecruitmentStatus status;

    @CreationTimestamp
    private LocalDateTime postingDate;
    @UpdateTimestamp
    private LocalDateTime modifyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_member_id")
    private CompanyMember companyMember;

    // 자동으로 status OPEN 으로 설정
    public void setOpen() {
        this.status = RecruitmentStatus.OPEN;
    }

    // parsing
    public RecruitmentDTO.Response toDTO() {
        return RecruitmentDTO.Response.builder()
                                        .recruitmentId(this.id)
                                        .title(this.title)
                                        .recruiterCount(this.recruiterCount)
                                        .closingDate(this.closingDate)
                                        .status(this.status)
                                        .modifyDate(this.modifyDate)
                                        .postingDate(this.postingDate)
                                        .companyMemberId(this.companyMember.getId())
                                        .companyName(this.companyMember.getCompanyName())
                                        .build();
    }

    // update
    public Recruitment update(RecruitmentDTO.Request request) {
        this.title = request.title();
        this.recruiterCount = request.recruiterCount();
        this.closingDate = request.closingDate();
        this.status = request.status();
        return this;
    }
}