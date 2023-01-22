package ru.javarush.quest.stepanov.questdelta.Repository;

import ru.javarush.quest.stepanov.questdelta.Entity.User;

import java.util.stream.Stream;

public class UserRepository extends QuestEntityRepository<User> {

    private static final UserRepository INSTANCE = new UserRepository();

    private UserRepository(){}

    public static UserRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public Stream<User> find(User searchUser) {
        return inMemoryStorage.values().stream()
                .filter(user ->
                        isOk(searchUser, user, User::getId) &&
                                isOk(searchUser, user, User::getLogin) &&
                                isOk(searchUser, user, User::getPassword) &&
                                isOk(searchUser, user, User::getEmail) &&
                                isOk(searchUser, user, User::getRole)
                );
    }
}
