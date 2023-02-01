package ru.javarush.quest.stepanov.questdelta.repository;

import ru.javarush.quest.stepanov.questdelta.entity.Game;

import java.util.stream.Stream;

public class GameRepository extends QuestEntityRepository<Game>{

    @Override
    public Stream<Game> find(Game entity) {
        return inMemoryStorage.values().stream()
                .filter(game ->
                        isOk(entity, game, Game::getId) &&
                                isOk(entity, game, Game::getUser) &&
                                isOk(entity, game, Game::getGameState) &&
                                isOk(entity, game, Game::getLastRedirectedQuestion) &&
                                isOk(entity, game, Game::getQuest)
                );
    }
}
