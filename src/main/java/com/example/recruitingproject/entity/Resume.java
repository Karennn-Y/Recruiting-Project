package com.example.recruitingproject.entity;

import com.example.recruitingproject.dto.ResumeDTO;
import com.example.recruitingproject.enums.ResumeStatus;
import com.example.recruitingproject.util.EducationListJsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "resume_id")
    private Long id;
    private String title;

    @Convert(converter = EducationListJsonConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Education> educationList;

    @Enumerated(EnumType.STRING)
    private ResumeStatus status;

//    @Column(updatable = false) -> 해결 완 ㅠ update (dirty check) 잡음.. jojoldu 참고..
    @CreationTimestamp
    private LocalDateTime postingDate;
    @UpdateTimestamp
    private LocalDateTime modifyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setOpen() {
        this.status = ResumeStatus.OPEN;
    }

    public ResumeDTO.Response toDTO() {
        return ResumeDTO.Response.builder()
            .resumeId(this.id)
            .title(this.title)
            .educationList(this.educationList.stream()
                                        .map(Education::toDTO)
                                        .toList())
            .status(this.status)
            .postingDate(this.postingDate)
            .modifyDate(this.modifyDate)
            .memberId(this.member.getId())
            .memberName(this.member.getName())
            .build();
    }

    public Resume update(ResumeDTO.Request request) {
        this.title = request.title();
        this.educationList = request.educationList().stream()
                                                    .map(e -> Education.builder()
                                                                    .degree(e.degree())
                                                                    .school(e.school())
                                                                    .build())
                                                    .toList();
        this.status = request.status();
        return this;
    }
}
