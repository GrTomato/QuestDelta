package ru.javarush.quest.stepanov.questdelta.service;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;
import ru.javarush.quest.stepanov.questdelta.entity.User;
import ru.javarush.quest.stepanov.questdelta.entity.UserRole;
import ru.javarush.quest.stepanov.questdelta.mapper.FormData;
import ru.javarush.quest.stepanov.questdelta.mapper.Mapper;
import ru.javarush.quest.stepanov.questdelta.repository.UserRepository;
import ru.javarush.quest.stepanov.questdelta.util.SessionParser;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void create(User entity){
        userRepository.create(entity);
    }
    public void update(User entity){
        userRepository.update(entity);
    }

    public void delete(User entity){
        userRepository.delete(entity);
    }

    public Collection<UserDTO> getAll(){
        return userRepository
                .getAll()
                .map(Mapper.user::getDTO)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
    public Optional<UserDTO> getById(long id){
        User userEntity = userRepository.getById(id);
        return Mapper.user.getDTO(userEntity);
    }

    public Optional<UserDTO> create(FormData formData){
        User parsedUser = Mapper.user.parse(formData);
        parsedUser.setRole(UserRole.PLAYER);
        userRepository.create(parsedUser);
        return Mapper.user.getDTO(parsedUser);
    }

    public Optional<UserDTO> getUser(FormData formData){
        User parsedUser = Mapper.user.parse(formData);
        User foundUser = this.getUserByLogin(parsedUser.getLogin()); // login should be unique
        return Mapper.user.getDTO(foundUser);
    }

    private User getUserByLogin(String login) {

        User searchUser = User.with()
                .login(login)
                .build();

        Optional<User> foundUser = userRepository
                .find(searchUser)
                .findFirst();

        return foundUser.orElse(null);
    }

    public static UserDTO getVisitorUser(){
        return UserDTO.with().role(UserRole.VISITOR).build();
    }

    public Optional<UserDTO> getUser(HttpSession session){
        Object user = SessionParser.getSessionUser(session);
        try{
            return Optional.ofNullable( (UserDTO) user);
        } catch (ClassCastException e){
            return Optional.empty();
        }
    }
}
