package ru.javarush.quest.stepanov.questdelta.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(builderMethodName = "with")
public class User extends QuestEntity{

    private String login;
    private String password;
    private UserRole role;
    private String email;

    public User(String login, String password, UserRole role, String email) {
        super();
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    @Override
    public String toString() {
        return login + " (" + email + ')';
    }
}
