package com.exxeta.prompts.like;

import com.exxeta.prompts.prompt.Prompt;
import com.exxeta.prompts.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "promptLike", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "prompt_id"})})
// lässt keine Kombinatiion aus den selben Wertpaaren zu
@Data
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    // Ein Benutzer kann viele Likes tätiges - aber ein Like ist einem bestimmten Nutzer zugeordnet

    @ManyToOne
    private Prompt prompt;
    // ein Prompt kann viele Likes haben - aber ein einzelnes Like ist einem bestimmten Prompt zugeordnet
}
