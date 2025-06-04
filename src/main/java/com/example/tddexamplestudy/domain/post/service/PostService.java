package com.example.tddexamplestudy.domain.post.service;

import com.example.tddexamplestudy.domain.post.dto.PostRequestDto;
import com.example.tddexamplestudy.domain.post.dto.PostResponseDto;
import com.example.tddexamplestudy.domain.post.entity.PostEntity;
import com.example.tddexamplestudy.domain.post.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long create(PostRequestDto dto) {
        if (dto.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(dto.getTitle());
        postEntity.setContent(dto.getContent());

        return postRepository.save(postEntity).getPostId();
    }

    public PostResponseDto getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id).get();
        PostResponseDto dto = new PostResponseDto();
        dto.setPostId(postEntity.getPostId());
        dto.setTitle(postEntity.getTitle());
        dto.setContent(postEntity.getContent());
        return dto;
    }
}
