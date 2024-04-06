package com.exxeta.prompts.like;

import com.exxeta.prompts.prompt.Prompt;
import com.exxeta.prompts.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "promptLike", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "prompt_id"})})
@Data
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Prompt prompt;

}
