package ru.javarush.quest.stepanov.questdelta.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(builderMethodName = "with")
public class AnswerDTO {

    private Long id;
    private String text;
    private QuestionDTO nextQuestion;

}
