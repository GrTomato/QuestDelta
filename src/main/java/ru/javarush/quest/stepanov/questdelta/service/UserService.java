package ru.javarush.quest.stepanov.questdelta.service;

import ru.javarush.quest.stepanov.questdelta.entity.User;
import ru.javarush.quest.stepanov.questdelta.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

public enum UserService {
    INSTANCE;
    private final UserRepository userRepository = UserRepository.getInstance();

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

    public Collection<User> getAll(){
        return userRepository
                .getAll()
                .toList();
    }
    public Optional<User> getById(long id){
        return userRepository.getById(id);
    }

    public Optional<User> getUserByLogin(String login) {

        // прописать сценарий ошибок, когда оба поля пустые

        User searchUser = User.with()
                .login(login)
                .build();

        return userRepository.find(searchUser).findFirst();
    }
}
