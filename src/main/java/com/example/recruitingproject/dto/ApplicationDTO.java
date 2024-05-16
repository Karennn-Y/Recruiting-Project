package com.example.recruitingproject.dto;

public class ApplicationDTO {
    public record Request (
        Long memberId,
        Long resumeId,
        Long applicationId
    ) {}
}
