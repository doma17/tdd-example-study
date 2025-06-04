package com.example.tddexamplestudy.api;

import com.example.tddexamplestudy.domain.post.dto.PostRequestDto;
import com.example.tddexamplestudy.domain.post.entity.PostEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 뷰 페이지 처리 컨트롤러
 */
@Controller
public class PageController {

    @GetMapping("/page")
    public String getPage(Model model) {
        List<PostEntity> postEntityList = new ArrayList<>();
        model.addAttribute("POSTLIST", postEntityList);

        return "page";
    }

    @PostMapping("/page")
    public String postPage(PostRequestDto dto) {
        return "redirect:/page";
    }
}