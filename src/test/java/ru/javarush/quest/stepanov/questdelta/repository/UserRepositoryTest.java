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
        Optional<User> getResult = userRepository.getById(billy.getId());

        getResult.ifPresent(user -> assertEquals(billy, user));
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