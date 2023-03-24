package dev.be.learnable.core.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
public class Choice {
    private Message message;
    private String finish_reason;
    private int index;
}