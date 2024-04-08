package com.exxeta.prompts.like;

import com.exxeta.prompts.prompt.Prompt;
import com.exxeta.prompts.prompt.PromptRepository;
import com.exxeta.prompts.user.User;
import com.exxeta.prompts.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PromptRepository promptRepository;
    private final UserService userService;

    public LikeService(LikeRepository likeRepository, PromptRepository promptRepository, UserService userService) {
        this.likeRepository = likeRepository;
        this.promptRepository = promptRepository;
        this.userService = userService;
    }

    public boolean likePrompt(Long promptId, Long userId) {
        Prompt prompt = promptRepository.findById(promptId).orElse(null);
        if (prompt == null) {
            return false; // prompt not found
        }

        // check if user already liked the prompt
        Like existingLike = likeRepository.findByPromptIdAndUserId(promptId, userId);
        if (existingLike != null) {
            return false; // user has already liked the prompt
        }

        Like like = new Like();
        like.setPrompt(prompt);

        Optional<User> user = userService.getUserById(userId);
        user.ifPresent(like::setUser);
        // like::setUser eq like.setUser(user.get())
        // wenn der usern icht exisitert aber der Like Button im Frontend gedrückt wird, wird ein Eintrag mit null als like gespeichert

        likeRepository.save(like);
        return true;
    }

    public boolean checkIfUserLikedPrompt(Long promptId, Long userId) {
        return likeRepository.existsByPromptIdAndUserId(promptId, userId);
    }
}
