package ru.javarush.quest.stepanov.questdelta.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(builderMethodName = "with")
public class Quest extends QuestEntity{

    private String name;
    private String description;
    private User author;
    private Question startQuestion;

    public Quest(String name, String description, User author, Question startQuestion) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.startQuestion = startQuestion;
    }

    @Override
    public String toString() {
        return "Quest{" +
                "name='" + name + '\'' +
                ", author=" + author +
                '}';
    }
}
