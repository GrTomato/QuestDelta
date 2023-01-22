package ru.javarush.quest.stepanov.questdelta.entity;

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