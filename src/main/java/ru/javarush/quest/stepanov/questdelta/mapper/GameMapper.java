package ru.javarush.quest.stepanov.questdelta.mapper;

import ru.javarush.quest.stepanov.questdelta.dto.GameDTO;
import ru.javarush.quest.stepanov.questdelta.dto.QuestDTO;
import ru.javarush.quest.stepanov.questdelta.dto.QuestionDTO;
import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;
import ru.javarush.quest.stepanov.questdelta.entity.Game;

import java.util.Optional;

public class GameMapper implements Mapper<Game, GameDTO> {

    @Override
    public Optional<GameDTO> getDTO(Game gameEntity) {

        Optional<UserDTO> userDTO = Mapper.user.getDTO(gameEntity.getUser());
        Optional<QuestionDTO> questionDTO = Mapper.question.getDTO(gameEntity.getLastRedirectedQuestion());
        Optional<QuestDTO> questDTO = Mapper.quest.getDTO(gameEntity.getQuest());

        return gameEntity != null
                ? Optional.ofNullable(
                        GameDTO
                                .with()
                                .id(gameEntity.getId())
                                .gameState(gameEntity.getGameState())
                                .user(userDTO.orElse(null))
                                .lastRedirectedQuestion(questionDTO.orElse(null))
                                .quest(questDTO.orElse(null))
                                .build())
                : Optional.empty();
    }

    @Override
    public Game parse(FormData formData) {
        Game parsedGame = Game.with().build();
        return fill(parsedGame, formData);
    }
}
