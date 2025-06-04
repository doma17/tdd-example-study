package com.example.tddexamplestudy.domain.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {
    private Long postId;
    private String title;
    private String content;
}
