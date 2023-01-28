package ru.javarush.quest.stepanov.questdelta.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javarush.quest.stepanov.questdelta.entity.User;
import ru.javarush.quest.stepanov.questdelta.entity.UserRole;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
class UserRepositoryTest {

    private UserRepository userRepository = UserRepository.getInstance();

    @BeforeEach
    void setUp() {
        userRepository.inMemoryStorage.clear();
    }

    @Test
    void UserRepositoryCreateTestSize() {
        User billy = User.with()
                .login("billy")
                .password("gogo")
                .email("billy@gmail.com")
                .role(UserRole.VISITOR)
                .build();
        userRepository.create(billy);

        assertEquals(userRepository.getInMemoryStorage().size(), 1);
    }

    @Test
    void UserRepositoryGetById() {
        User billy = User.with()
                .login("billy")
                .password("gogo")
                .email("billy@gmail.com")
                .role(UserRole.VISITOR)
                .build();
        userRepository.create(billy);
        User user = userRepository.getById(billy.getId());
        assertEquals(billy, user);
    }

    @Test
    void UserRepositoryGetByIdNull() {
        User user = userRepository.getById(666L);
        assertEquals(null, user);
    }

    @Test
    void UserRepositoryFindTest() {
        User billy = User.with()
                .login("billy")
                .password("gogo")
                .email("billy@gmail.com")
                .role(UserRole.VISITOR)
                .build();
        userRepository.create(billy);

        User searchUser = User.with()
                //.login("antony")
                //.password("123")
                .email("billy@gmail.com")
                //.role(UserRole.VISITOR)
                .build();
        Optional<User> user = userRepository.find(searchUser).findFirst();

        if (user.isPresent()){
            assertEquals(user.get(), billy);
        }
    }


}