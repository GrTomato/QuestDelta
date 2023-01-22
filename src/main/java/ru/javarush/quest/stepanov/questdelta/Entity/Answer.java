package ru.javarush.quest.stepanov.questdelta.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(builderMethodName = "with")
public class Answer extends QuestEntity {

    private String text;
    private Question nextQuestion;

}
