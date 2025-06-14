package com.example.tddexamplestudy.api;

import com.example.tddexamplestudy.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * PageController에 대한 테스트 클래스
 * 이 클래스는 페이지의 GET 및 POST 요청을 테스트
 */
@WebMvcTest(controllers = PageController.class)
@Import(TestSecurityConfig.class)
class PageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void get_method_테스트1() throws Exception {
        // given

        // when & then
        mockMvc.perform(get("/page"))
                .andExpect(status().isOk())
                .andExpect(view().name("page"))
                .andExpect(model().attributeExists("POSTLIST"));
        // andExpect 내에서 view, model을 사용하여
        // 응답의 뷰 이름과 모델 속성을 검증
    }

    @Test
    void post_method_테스트1() throws Exception {
        // given
        String title = "제목";
        String content = "내용";

        // when & then
        mockMvc.perform(post("/page")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", title)
                        .param("content", content))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/page"));
        // andExpect 내에서 상태 코드가 3xx 리다이렉션인지 확인하고
        // 리다이렉션 URL이 "/page"인지 검증, redirectedUrl("/page")를 사용
    }
}