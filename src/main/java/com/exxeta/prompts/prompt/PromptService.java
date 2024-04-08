package com.exxeta.prompts.prompt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptService {

    @Autowired
    private PromptRepository promptRepository;

    public Prompt createPrompt(Prompt prompt) {
        return promptRepository.save(prompt);
    }

    public List<Prompt> getPrompts() {
        return promptRepository.findAll();
    }

    public List<Prompt> getAllByUserId(Long userId) {
        return promptRepository.findByUserId(userId);
    }

    public void deletePrompt(Long promptId) {
        if(promptRepository.existsById(promptId)) {
            promptRepository.deleteById(promptId);
        } else {
            throw new ResourceNotFoundException("Prompt mit der ID " + promptId + " wurde nicht gefunden");
        }
    }
}
