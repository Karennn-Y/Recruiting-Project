package com.example.recruitingproject.entity;

import com.example.recruitingproject.dto.ResumeDTO.EducationDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Education {
    private String school;
    private Integer degree; // 0.고졸  1.대졸  2.석사  3.박사

    public EducationDTO toDTO() {
        return EducationDTO.builder()
                            .degree(this.degree)
                            .school(this.school)
                            .build();
    }
}
