package com.example.recruitingproject.dto;

import com.example.recruitingproject.entity.Recruitment;
import com.example.recruitingproject.enums.RecruitmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

public class RecruitmentDTO {
    // JAVA 17 -> record 지원
    public record Request (
        String title,
        Integer recruiterCount,
        @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime closingDate,
        String companyLoginId,
        RecruitmentStatus status
    ) {
        public Recruitment toEntity () {
           return Recruitment.builder()
                                .title(title)
                                .recruiterCount(recruiterCount)
                                .closingDate(closingDate)
                                .build();
        }
    }

    @Builder
    @Getter
    public static class Response {
        private Long recruitmentId;
        private String title;
        private Integer recruiterCount;
        private LocalDateTime closingDate;
        private RecruitmentStatus status;
        private LocalDateTime modifyDate;
        private LocalDateTime postingDate;
        private Long companyMemberId;
        private String companyName;
    }

    // JAVA 17 아래로..
//    @ToString
//    @Setter
//    public static class Request {
//        private String title;
//        private Integer recruiterCount;
//        @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
//        private LocalDateTime closingDate;
//        private String companyMemberLoginId;
//    }
}
