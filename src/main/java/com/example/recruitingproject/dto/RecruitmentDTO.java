package com.example.recruitingproject.dto;

import com.example.recruitingproject.entity.Recruitment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;

public class RecruitmentDTO {
    // JAVA 17 -> record 지원
    public record Request (
        String title,
        Integer recruiterCount,
        @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime closingDate,
        String companyLoginId
    ) {
        public Recruitment toEntity () {
           return Recruitment.builder()
                                .title(title)
                                .recruiterCount(recruiterCount)
                                .closingDate(closingDate)
                                .build();
        }
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
