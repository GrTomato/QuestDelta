package ru.javarush.quest.stepanov.questdelta.Services;

import ru.javarush.quest.stepanov.questdelta.Entity.User;
import ru.javarush.quest.stepanov.questdelta.Repository.UserRepository;

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

    public Optional<User> getUser(String login, String password) {

        // прописать сценарий ошибок, когда оба поля пустые

        User searchUser = User.with()
                .login(login)
                .password(password)
                .build();

        return userRepository.find(searchUser).findFirst();
    }
}
