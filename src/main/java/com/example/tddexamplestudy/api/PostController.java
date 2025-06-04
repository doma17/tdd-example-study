package com.example.tddexamplestudy.api;

import com.example.tddexamplestudy.domain.post.dto.PostRequestDto;
import com.example.tddexamplestudy.domain.post.dto.PostResponseDto;
import com.example.tddexamplestudy.domain.post.service.PostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public ResponseEntity<?> postMethod(
            @RequestBody PostRequestDto postRequestDto
            ) {

        Long resultId = postService.create(postRequestDto);
        Map<String, Object> response = Map.of("id", resultId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json"));

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") Long id) {
        PostResponseDto response = postService.getPostById(id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json"));

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }
}
