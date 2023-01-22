package ru.javarush.quest.stepanov.questdelta.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class QuestEntity {

    private Long id;

    public QuestEntity() {

    }

    @Override
    public String toString() {
        return "QuestEntity{" +
                "id=" + id +
                '}';
    }
}
