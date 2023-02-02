package ru.javarush.quest.stepanov.questdelta.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.javarush.quest.stepanov.questdelta.config.Winter;
import ru.javarush.quest.stepanov.questdelta.entity.User;
import ru.javarush.quest.stepanov.questdelta.entity.UserRole;
import ru.javarush.quest.stepanov.questdelta.service.InitService;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
class UserRepositoryTest {

    private static final UserRepository userRepository = Winter.getBean(UserRepository.class);

    @BeforeEach
    void setUp() {
        userRepository.create(
                User.with().login("Anthony").password("123").email("Anthony@gmail.com").role(UserRole.PLAYER).build()
        );
    }

    @AfterEach
    void tearDown() {
        userRepository.inMemoryStorage.clear();
    }

    public static Stream<Arguments> getSampleUsers(){
        return Stream.of(
                Arguments.of(User.with().login("Anthony").build(), 1),
                Arguments.of(User.with().login("Jack").password("wrongPass").build(), 0),
                Arguments.of(User.with().email("Anthony@gmail.com").role(UserRole.PLAYER).build(), 1)
        );
    }

    @ParameterizedTest
    @MethodSource("getSampleUsers")
    @DisplayName("Run Find method test with differents params")
    void UserRepositoryFindTest(User user, int count) {
        long countFoundUsers = userRepository.find(user).count();
        assertEquals(countFoundUsers, count);
    }


}