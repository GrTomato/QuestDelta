package ru.javarush.quest.stepanov.questdelta.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Builder(builderMethodName = "with")
public class Question extends QuestEntity {
    String text;
    GameState gameState;
    final Collection<Answer> answers = new ArrayList<>();

}
