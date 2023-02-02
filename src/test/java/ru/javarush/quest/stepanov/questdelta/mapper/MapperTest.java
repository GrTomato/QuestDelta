package ru.javarush.quest.stepanov.questdelta.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.javarush.quest.stepanov.questdelta.config.Winter;
import ru.javarush.quest.stepanov.questdelta.dto.GameDTO;
import ru.javarush.quest.stepanov.questdelta.dto.QuestDTO;
import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;
import ru.javarush.quest.stepanov.questdelta.entity.*;
import ru.javarush.quest.stepanov.questdelta.repository.GameRepository;
import ru.javarush.quest.stepanov.questdelta.repository.UserRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {

    private static final GameRepository gameRepository = Winter.getBean(GameRepository.class);
    private static final UserRepository userRepository = Winter.getBean(UserRepository.class);

    public static Stream<Arguments> getSampleEntities(){
        Question nextQuestion = Question.with().build();
        Question startQuestion = Question.with().build();
        startQuestion.getAnswers().add(Answer.with().nextQuestion(nextQuestion).build());
        return Stream.of(
                Arguments.of(
                        Game.with()
                                .quest(Quest.with().name("testQuest").startQuestion(startQuestion).build())
                                .user(User.with().login("user1").password("123").build())
                                .lastRedirectedQuestion(startQuestion)
                                .build(),
                        GameDTO.with().user(UserDTO.with().login("user1").build()).quest(QuestDTO.with().name("testQuest").build()).build()
                )
        );
    }

    public static Stream<Arguments> getFrontData(){

        User dummyUser = User.with().login("testUser").build();
        userRepository.create(dummyUser);
        Game savedGame = Game.with().user(dummyUser).build();
        gameRepository.create(savedGame);
        Game resultGame = Game.with().build();
        resultGame.setId(savedGame.getId());
        resultGame.setUser(savedGame.getUser());

        return Stream.of(
                Arguments.of(
                        savedGame.getId(),
                        dummyUser.getId(),
                        resultGame
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getSampleEntities")
    @DisplayName("Convert entities to DTO")
    void EntitiesToDTOConverterTest(Game game, GameDTO gameDTO){
        Optional<GameDTO> dto = Mapper.game.getDTO(game);
        if (dto.isPresent()){
            assertEquals(gameDTO.getUser().getLogin(), dto.get().getUser().getLogin());
        } else {
            fail("No DTO was created");
        }
    }

    @ParameterizedTest
    @MethodSource("getFrontData")
    @DisplayName("Check whether mapper.fill fills new instances correctly.")
    void FormDataToEntitiesTest(Long gameId, Long userId, Game game) {
        FormData formData = Mockito.mock(FormData.class);
        Mockito.when(formData.getParameter("game_id")).thenReturn(gameId.toString());
        Mockito.when(formData.getParameter("user_id")).thenReturn(userId.toString());
        User parsedUser = Mapper.user.parse(formData);
        Game parsedGame = Mapper.game.parse(formData);
        parsedGame.setUser(parsedUser);

        if (
                Objects.equals(parsedGame.getId(), game.getId()) && Objects.equals(parsedGame.getUser().getId(), game.getUser().getId())
        ){
            assertTrue(true);
        } else {
            fail();
        }
    }
}