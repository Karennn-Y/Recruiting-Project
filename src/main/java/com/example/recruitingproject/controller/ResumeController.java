package com.example.recruitingproject.controller;

import com.example.recruitingproject.dto.ResumeDTO;
import com.example.recruitingproject.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    
    @PostMapping("/resumes")
    public void postingResume(@RequestBody ResumeDTO.Request request) {
        resumeService.postingResume(request);
    }
}
