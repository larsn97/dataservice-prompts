package com.exxeta.prompts.prompt;

import com.exxeta.prompts.like.Like;
import com.exxeta.prompts.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity(name = "prompts")
public class Prompt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp_time", nullable = false, updatable = false)
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "prompt", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("prompt")
    private List<Like> likes;

}
