package com.exxeta.prompts.prompt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePromptRequest {
    private String description;
    private Long userId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
