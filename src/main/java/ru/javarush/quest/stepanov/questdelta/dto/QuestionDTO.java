package ru.javarush.quest.stepanov.questdelta.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@ToString
@Getter
@Setter
@Builder(builderMethodName = "with")
public class QuestionDTO {

    private Long id;
    private String text;
    private Collection<AnswerDTO> answers;
}
