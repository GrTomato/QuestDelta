package ru.javarush.quest.stepanov.questdelta.mapper;

import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;
import ru.javarush.quest.stepanov.questdelta.entity.User;

import java.util.Optional;

public class UserMapper implements Mapper<User, UserDTO>{

    @Override
    public Optional<UserDTO> getDTO(User userEntity) {
        return userEntity != null
                ? Optional.of(
                        UserDTO
                                .with()
                                .id(userEntity.getId())
                                .login(userEntity.getLogin())
                                .email(userEntity.getEmail())
                                .role(userEntity.getRole())
                                .build())
                : Optional.empty();
    }

    @Override
    public User parse(FormData formData) {
        User parsedUser = User.with().build();
        return fill(parsedUser, formData);
    }

}
