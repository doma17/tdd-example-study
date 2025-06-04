package com.example.tddexamplestudy.integration;

import com.example.tddexamplestudy.config.TestSecurityConfig;
import com.example.tddexamplestudy.domain.post.dto.PostRequestDto;
import com.example.tddexamplestudy.domain.post.entity.PostEntity;
import com.example.tddexamplestudy.domain.post.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc // MockMvc를 자동으로 설정
@ActiveProfiles("test") // 테스트 프로파일을 활성화
@Transactional // 테스트 후 데이터베이스 롤백
@Import(TestSecurityConfig.class)
public class PostControllerIntegration {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PostRepository postRepository;

    @Test
    void post_통합테스트1() throws Exception {
        // given
        PostRequestDto dto = new PostRequestDto();
        dto.setTitle("타이틀1");
        dto.setContent("내용1");

        // when & then
         mockMvc.perform(post("/post")
                 .with(csrf())
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(new ObjectMapper().writeValueAsString(dto)))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                 .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void get_통합테스트1() throws Exception {
        // given
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle("타이틀1");
        postEntity.setContent("내용1");
        Long savedId = postRepository.save(postEntity).getPostId();

        // when & then
        mockMvc.perform(get("/post/" + savedId)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.postId").value(savedId))
                .andExpect(jsonPath("$.title").value("타이틀1"))
                .andExpect(jsonPath("$.content").value("내용1"));
    }
}
