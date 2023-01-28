package ru.javarush.quest.stepanov.questdelta.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.javarush.quest.stepanov.questdelta.entity.UserRole;

@Getter
@Setter
@Builder(builderMethodName = "with")
public class UserDTO {
    private Long id;
    private String login;
    private UserRole role;
    private String email;

    @Override
    public String toString() {
        return login + " (" + email + ')';
    }
}
