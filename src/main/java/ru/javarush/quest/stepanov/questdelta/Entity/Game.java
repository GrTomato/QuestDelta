package ru.javarush.quest.stepanov.questdelta.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(builderMethodName = "with")
public class Game extends QuestEntity {

    private Quest quest;
    private User user;
    private GameState gameState;
    private Question lastRedirectedQuestion;

    @Override
    public String toString() {
        return "Game{" +
                "quest=" + quest +
                ", user=" + user +
                ", gameState=" + gameState +
                ", lastRedirectedQuestion=" + lastRedirectedQuestion +
                '}';
    }
}
