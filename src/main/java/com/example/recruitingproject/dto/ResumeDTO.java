package com.example.recruitingproject.dto;

import com.example.recruitingproject.entity.Education;
import com.example.recruitingproject.entity.Resume;
import com.example.recruitingproject.enums.ResumeStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class ResumeDTO {
    // JAVA 17 -> record 지원
    public record Request (
        String title,
        List<EducationDTO> educationList,
        String loginId,
        ResumeStatus status
    ) {
        public Resume toEntity () {
           return Resume.builder()
                           .title(title)
                           .educationList(educationList.stream()
                                                        .map(e -> Education.builder()
                                                                            .school(e.school)
                                                                            .degree(e.degree)
                                                                            .build())
                                                        .toList())
                           .build();
        }
    }

    @Builder
    @Getter
    public static class Response {
        private Long resumeId;
        private String title;
        private List<EducationDTO> educationList;
        private ResumeStatus status;
        private LocalDateTime postingDate;
        private LocalDateTime modifyDate;
        private Long memberId;
        private String memberName;
    }

    @Builder
    public record EducationDTO (
        String school,
        Integer degree
    ) {
    }
}
