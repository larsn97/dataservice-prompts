package com.exxeta.prompts.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByPromptIdAndUserId(Long promptId, Long userId);
    boolean existsByPromptIdAndUserId(Long promptId, Long userId);
}
