package com.exxeta.prompts.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping()
    public ResponseEntity<String> likePrompt(@RequestBody LikeRequestDTO likeRequestDTO) {
        boolean liked = likeService.likePrompt(likeRequestDTO.getPromptId(), likeRequestDTO.getUserId());
        if (liked) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/liked")
    public ResponseEntity<Boolean> checkIfUserLikedPrompt(@RequestParam(value = "promptId") Long promptId, @RequestParam(value="userId") Long userId) {
        boolean likedByCurrentUser = likeService.checkIfUserLikedPrompt(promptId, userId);
        return ResponseEntity.ok(likedByCurrentUser);
    }
}
