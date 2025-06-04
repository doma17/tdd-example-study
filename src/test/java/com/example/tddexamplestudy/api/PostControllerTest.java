package com.example.tddexamplestudy.api;

import com.example.tddexamplestudy.config.TestSecurityConfig;
import com.example.tddexamplestudy.domain.post.dto.PostRequestDto;
import com.example.tddexamplestudy.domain.post.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * PostController에 대한 테스트 클래스
 * 이 클래스는 POST 요청을 테스트
 */
@WebMvcTest(controllers = PostController.class) // PostController를 테스트하기 위한 설정
//@Import(TestSecurityConfig.class) // TestSecurityConfig를 사용하여 테스트 환경에서 보안 설정을 적용
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    PostService postService;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void post_method_테스트1() throws Exception {
        //given
        PostRequestDto postRequestDto = new PostRequestDto();
        postRequestDto.setTitle("타이틀1");
        postRequestDto.setContent("내용1");

        //when & then
        mockMvc.perform(post("/post")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void post_method_테스트2() throws Exception {
        //given
        PostRequestDto postRequestDto = new PostRequestDto();
        postRequestDto.setTitle("타이틀1");
        postRequestDto.setContent("내용1");

        given(postService.create(any(PostRequestDto.class))).willReturn(1L);

        //when & then
        mockMvc.perform(post("/post")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(postService).create(any(PostRequestDto.class));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void post_method_테스트3() throws Exception {
        //given
        PostRequestDto postRequestDto = new PostRequestDto();
        postRequestDto.setTitle("");
        postRequestDto.setContent("내용1");

        given(postService.create(any(PostRequestDto.class))).willThrow(new IllegalArgumentException());

        //when & then
        mockMvc.perform(post("/post")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postRequestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}