package ru.javarush.quest.stepanov.questdelta.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Builder(builderMethodName = "with")
public class Question extends QuestEntity {
    String text;

    Collection<Answer> answers;

}
