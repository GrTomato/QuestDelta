package ru.javarush.quest.stepanov.questdelta.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder(builderMethodName = "with")
public class AnswerDTO {

    private Long id;
    private String text;
    private QuestionDTO nextQuestion;

}
