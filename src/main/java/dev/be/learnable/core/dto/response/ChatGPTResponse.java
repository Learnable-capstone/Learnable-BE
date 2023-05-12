package dev.be.learnable.core.dto.response;

import lombok.AllArgsConstructor;
import dev.be.learnable.core.dto.Choice;
import dev.be.learnable.core.dto.Usage;
import lombok.Setter;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ChatGPTResponse implements Serializable {
    private String id;
    private String object;
    private String model;
    private LocalDate created;
    private List<Choice> choices;
    private Usage usage;

    public String getContent(){
        if(choices != null && !choices.isEmpty()){
            return choices.get(0).getMessage().getContent();
        }
        return null;
    }
}