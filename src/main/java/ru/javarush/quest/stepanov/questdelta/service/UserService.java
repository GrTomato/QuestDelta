package ru.javarush.quest.stepanov.questdelta.service;

import jakarta.servlet.http.HttpSession;
import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;
import ru.javarush.quest.stepanov.questdelta.entity.User;
import ru.javarush.quest.stepanov.questdelta.entity.UserRole;
import ru.javarush.quest.stepanov.questdelta.exception.NoUserFoundException;
import ru.javarush.quest.stepanov.questdelta.mapper.FormData;
import ru.javarush.quest.stepanov.questdelta.mapper.Mapper;
import ru.javarush.quest.stepanov.questdelta.repository.UserRepository;
import ru.javarush.quest.stepanov.questdelta.util.SessionParser;

import java.util.Collection;
import java.util.Optional;

public enum UserService {
    INSTANCE;
    private final UserRepository userRepository = UserRepository.getInstance();
    private final UserDTO visitorUser = UserDTO.with().role(UserRole.VISITOR).build();

    private UserService() {}

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
        Optional<User> foundUser = this.checkUserByLogin(parsedUser.getLogin()); // login should be unique

        if (foundUser.isPresent()){
            return Mapper.user.getDTO(foundUser.get());
        } else {
            throw new NoUserFoundException();
        }
    }

    private Optional<User> checkUserByLogin(String login) {
        if (!login.isEmpty() && !login.isBlank()){
            User searchUser = User.with()
                    .login(login)
                    .build();

            return userRepository
                    .find(searchUser)
                    .findFirst();
        } else {
            return Optional.empty();
        }
    }

    public UserDTO getVisitorUser(){
        return this.visitorUser;
    }

    public UserDTO getUser(HttpSession session){
        Object user = SessionParser.getSessionUser(session);
        try{
            return (UserDTO) user;
        } catch (ClassCastException e){
            throw new RuntimeException("Can not cast session attribute 'user' to UserDTO. This attribute should store something else.");
        }
    }
}
