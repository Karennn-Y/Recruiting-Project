package com.example.recruitingproject.controller;

import com.example.recruitingproject.dto.RecruitmentDTO;
import com.example.recruitingproject.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    @PostMapping("/recruitments")
    public void postRecruitment(@RequestBody RecruitmentDTO.Request request) {
        recruitmentService.postRecruitment(request);
    }
}
