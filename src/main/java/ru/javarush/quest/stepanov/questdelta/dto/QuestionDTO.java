package ru.javarush.quest.stepanov.questdelta.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Builder(builderMethodName = "with")
public class QuestionDTO {

    private Long id;
    private String text;
    private Collection<AnswerDTO> answers;
}
