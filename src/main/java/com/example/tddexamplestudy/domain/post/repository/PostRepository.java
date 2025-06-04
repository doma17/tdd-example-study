package com.example.tddexamplestudy.domain.post.repository;

import com.example.tddexamplestudy.domain.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
