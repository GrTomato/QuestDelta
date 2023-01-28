package ru.javarush.quest.stepanov.questdelta.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(builderMethodName = "with")
public class QuestDTO {

    private Long id;
    private String name;
    private String description;
    private UserDTO author;
    private QuestionDTO startQuestion;

}
