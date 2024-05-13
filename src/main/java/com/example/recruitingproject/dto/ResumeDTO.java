package com.example.recruitingproject.dto;

import com.example.recruitingproject.entity.Education;
import com.example.recruitingproject.entity.Recruitment;
import com.example.recruitingproject.entity.Resume;
import com.example.recruitingproject.enums.RecruitmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class ResumeDTO {
    // JAVA 17 -> record 지원
    public record Request (
        String title,
        List<EducationDTO> educationList,
        String loginId
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

//    @Builder
//    @Getter
//    public static class Response {
//        private Long recruitmentId;
//        private String title;
//        private Integer recruiterCount;
//        private LocalDateTime closingDate;
//        private RecruitmentStatus status;
//        private LocalDateTime modifyDate;
//        private LocalDateTime postingDate;
//        private Long companyMemberId;
//        private String companyName;
//    }

    public record EducationDTO (
        String school,
        Integer degree
    ) {
    }
}
