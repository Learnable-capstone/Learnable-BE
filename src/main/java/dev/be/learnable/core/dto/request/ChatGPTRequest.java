package dev.be.learnable.core.dto.request;

import dev.be.learnable.core.dto.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ChatGPTRequest implements Serializable {
    private String model;
    private List<Message> messages;

    public ChatGPTRequest(String model, List<Message> messages){
        this.model = model;
        this.messages = messages;
    }
}