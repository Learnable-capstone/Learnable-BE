package dev.be.learnable.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@Getter
@Setter
public class Message implements Serializable {
    private String role;
    private String content;

    public Message(String role, String content){
        this.role = role;
        this.content = content;
    }
}