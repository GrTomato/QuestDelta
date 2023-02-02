package ru.javarush.quest.stepanov.questdelta.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.javarush.quest.stepanov.questdelta.config.Winter;
import ru.javarush.quest.stepanov.questdelta.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class QuestEntityRepositoryTest {

    private static final UserRepository userRepository = Winter.getBean(UserRepository.class);


    @BeforeAll
    static void beforeAll() {
    }

    @AfterAll
    static void afterAll() {
        userRepository.inMemoryStorage.clear();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        userRepository.inMemoryStorage.clear();
    }

    public static Stream<Arguments> getSampleUsers(){
        return Stream.of(
                Arguments.of(List.of(User.with().login("Will").build(), User.with().login("Boris").password("wrongPass").build()), 2),
                Arguments.of(List.of(User.with().login("Kenny").email("kenny@gmail.com").build()), 1)
        );
    }

    public static Stream<Arguments> getUsersUpdatedValues(){
        return Stream.of(
                Arguments.of(User.with().login("Will").build(), "newTestPassword"),
                Arguments.of(User.with().login("Kenny").email("kenny@gmail.com").build(), "neNewWsdlkfsldkf123123")
        );
    }

    @ParameterizedTest
    @MethodSource("getSampleUsers")
    @DisplayName("Run Create method test")
    void QuestEntityRepoCreateTest(List<User> users, int count) {
        users.forEach(userRepository::create);
        int actualSize = userRepository.getInMemoryStorage().size();
        assertEquals(actualSize, count);

    }

    @ParameterizedTest
    @MethodSource("getSampleUsers")
    @DisplayName("Run GetAll method test")
    void QuestEntityRepoGetAllTest(List<User> users, int count) {
        users.forEach(userRepository::create);
        long actualCount = userRepository.getAll().count();
        assertEquals(actualCount, count);
    }

    @ParameterizedTest
    @MethodSource("getSampleUsers")
    @DisplayName("Run Delete method test")
    void QuestEntityRepoDeleteTest(List<User> users, int count) {
        users.forEach(userRepository::create);
        Optional<User> firstFoundUser = userRepository.getAll().findFirst();
        if (firstFoundUser.isPresent()){
            userRepository.delete(firstFoundUser.get());
            assertEquals(userRepository.getInMemoryStorage().size(), count-1);
        } else {
            fail();
        }
    }

    @ParameterizedTest
    @MethodSource("getUsersUpdatedValues")
    @DisplayName("Run Update method test")
    void QuestEntityRepoUpdatePasswordTest(User dummyUser, String newPassword) {
        userRepository.create(dummyUser);
        dummyUser.setPassword(newPassword);
        userRepository.update(dummyUser);

        User loadedUser = userRepository.getById(dummyUser.getId());
        assertEquals(loadedUser.getPassword(), newPassword);
    }






}