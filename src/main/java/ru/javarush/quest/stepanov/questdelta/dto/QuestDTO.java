package ru.javarush.quest.stepanov.questdelta.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
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
