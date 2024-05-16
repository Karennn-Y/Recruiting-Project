package com.example.recruitingproject.controller;

import com.example.recruitingproject.dto.ApplicationDTO;
import com.example.recruitingproject.dto.RecruitmentDTO;
import com.example.recruitingproject.service.RecruitmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/recruitments")
    public List<RecruitmentDTO.Response> getRecruitmentList() {
        return recruitmentService.getRecruitmentList();
    }

    @GetMapping("/recruitments/{id}")
    public RecruitmentDTO.Response getRecruitment(@PathVariable(name = "id") Long recruitmentId) {
        return recruitmentService.getRecruitment(recruitmentId);
    }

    @PutMapping("/recruitments/{id}")
    public RecruitmentDTO.Response updateRecruitment(@PathVariable(name = "id") Long recruitmentId,
                                                        @RequestBody RecruitmentDTO.Request request) {
        return recruitmentService.updateRecruitment(recruitmentId, request);
    }

    @DeleteMapping("/recruitments/{id}")
    public void deleteRecruitment(@PathVariable(name = "id") Long recruitmentId,
                                    @RequestBody RecruitmentDTO.Request request) {
        recruitmentService.deleteRecruitment(recruitmentId, request);
    }

    @PostMapping("/recruitments/{id}/applications")
    public void applyRecruitment(@PathVariable(name = "id") Long recruitmentId,
        @RequestBody ApplicationDTO.Request request) {
        recruitmentService.applyRecruitment(recruitmentId, request);
    }
}
