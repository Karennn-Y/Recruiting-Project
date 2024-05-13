package com.example.recruitingproject.controller;

import com.example.recruitingproject.dto.ResumeDTO;
import com.example.recruitingproject.service.ResumeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    
    @PostMapping("/resumes")
    public void postingResume(@RequestBody ResumeDTO.Request request) {
        resumeService.postingResume(request);
    }

    @GetMapping("/resumes")
    public List<ResumeDTO.Response> getResumeList(@RequestParam String loginId) {
        // TODO : login 구현 후 session으로 변경해야 됨!!!!!
        return resumeService.getResumeList(loginId);
    }

    @GetMapping("/resumes/{id}")
    public ResumeDTO.Response getResume(@PathVariable(name = "id") Long resumeId, @RequestParam String loginId) {
        return resumeService.getResume(resumeId, loginId);
    }
}
