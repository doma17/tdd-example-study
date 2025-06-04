package com.example.tddexamplestudy.domain.service;

import com.example.tddexamplestudy.domain.post.dto.PostRequestDto;
import com.example.tddexamplestudy.domain.post.entity.PostEntity;
import com.example.tddexamplestudy.domain.post.repository.PostRepository;
import com.example.tddexamplestudy.domain.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    PostService postService;

    @Mock
    PostRepository postRepository;

    @Test
    void create_테스트1() {
        //given
        PostRequestDto requestDto = new PostRequestDto();
        requestDto.setTitle("타이틀1");
        requestDto.setContent("내용1");

        PostEntity saved = new PostEntity();
        ReflectionTestUtils.setField(saved, "postId", 1L);
        saved.setTitle("타이틀");
        saved.setContent("내용");
        given(postRepository.save(any(PostEntity.class))).willReturn(saved);

        //when
        Long resultId = postService.create(requestDto);

        //then
        assertInstanceOf(Long.class, resultId);
    }

    @Test
    void create_테스트2() {
        //given
        PostRequestDto requestDto = new PostRequestDto();
        requestDto.setTitle("타이틀1");
        requestDto.setContent("내용1");

        PostEntity saved = new PostEntity();
        ReflectionTestUtils.setField(saved, "postId", 1L);
        saved.setTitle("타이틀");
        saved.setContent("내용");
        given(postRepository.save(any(PostEntity.class))).willReturn(saved);

        //when
        postService.create(requestDto);

        //then
        verify(postRepository).save(any(PostEntity.class));
    }

    @Test
    void create_테스트3() {
        //given
        PostRequestDto requestDto = new PostRequestDto();
        requestDto.setTitle("");
        requestDto.setContent("내용1");

        // when & then
        assertThrows(IllegalArgumentException.class, () ->
                postService.create(requestDto));
    }
}