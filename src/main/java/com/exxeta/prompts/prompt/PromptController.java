package com.exxeta.prompts.prompt;

import com.exxeta.prompts.user.User;
import com.exxeta.prompts.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/prompts")
public class PromptController {

    private final PromptService promptService;
    private final UserService userService;

    public PromptController(PromptService promptService, UserService userService) {
        this.promptService = promptService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<Prompt> createPrompt(@RequestBody CreatePromptRequest request) {
        Prompt prompt = new Prompt();
        prompt.setDescription(request.getDescription());

        User user = userService.getUserById(request.getUserId()).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        prompt.setUser(user);

        Prompt savedPrompt = promptService.createPrompt(prompt);
        return new ResponseEntity<>(savedPrompt, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Prompt>> getAllPrompts() {
        List<Prompt> prompts = promptService.getPrompts();
        return new ResponseEntity<>(prompts, HttpStatus.OK);
    }

    @GetMapping(path="/user")
    public ResponseEntity<List<Prompt>> getPromptsByUserId(@RequestParam(value="userId") Long userId) {
        List<Prompt> prompts = promptService.getAllByUserId(userId);
        if (prompts.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(prompts, HttpStatus.OK);
    }


}
