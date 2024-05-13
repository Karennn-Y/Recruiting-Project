package com.example.recruitingproject.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Education {
    private String school;
    private Integer degree; // 0.고졸  1.대졸  2.석사  3.박사
}
