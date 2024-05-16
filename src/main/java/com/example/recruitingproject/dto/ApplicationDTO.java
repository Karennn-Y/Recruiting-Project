package com.example.recruitingproject.dto;

import com.example.recruitingproject.dto.ResumeDTO.EducationDTO;
import com.example.recruitingproject.enums.ApplicationStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class ApplicationDTO {
    public record Request (
        Long memberId,
        Long resumeId,
        Long applicationId
    ) {}

    @Builder
    @Getter
    public static class Response {
        private Long applicationId;
        private ApplicationStatus status;
        private LocalDateTime appliedDate;
        private Long resumeId;
        private String resumeTitle;
        private List<EducationDTO> educationList;
        private String name;
    }
}
