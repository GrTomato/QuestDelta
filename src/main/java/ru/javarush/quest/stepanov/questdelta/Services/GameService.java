package ru.javarush.quest.stepanov.questdelta.Services;

import ru.javarush.quest.stepanov.questdelta.Entity.*;
import ru.javarush.quest.stepanov.questdelta.Repository.GameRepository;

import java.util.Collection;
import java.util.Optional;

public enum GameService{

    INSTANCE;
    private final GameRepository gameRepository = GameRepository.getInstance();
    private final QuestService questService = QuestService.INSTANCE;
    private final UserService userService = UserService.INSTANCE;

    private GameService() {}


    public void restartGame(Game game){
        Question startQuestion = game.getQuest().getStartQuestion();
        game.setLastRedirectedQuestion(startQuestion);
        gameRepository.update(game);
    }
    public Game getGame(Long questId, Long userId){

        // move this logic to DTO
        Quest searchQuest = questService.getById(questId).isPresent() ? questService.getById(questId).get() : null;
        User searchUser = userService.getById(userId).isPresent() ? userService.getById(userId).get() : null;

        Game searchGame = Game.with()
                .quest(searchQuest)
                .gameState(GameState.PROGRESS)
                .user(searchUser)
                .build();

        Optional<Game> foundGame = gameRepository.find(searchGame).findFirst();
        Game currentGame = foundGame.orElseGet(() -> createNewGame(questId, userId));

        return currentGame;
    }

    private Game createNewGame(Long questId, Long userId){

        // get rid of condition
        Quest quest = questService.getById(questId).isPresent() ? questService.getById(questId).get() : null;
        User user = userService.getById(userId).isPresent() ? userService.getById(userId).get() : null;

        // fix
        assert quest != null;
        Game newGame = Game.with()
                .quest(quest)
                .gameState(GameState.PROGRESS)
                .user(user)
                .lastRedirectedQuestion(quest.getStartQuestion())
                .build();
        gameRepository.create(newGame);
        return newGame;
    }

    public void create(Game entity){
        gameRepository.create(entity);
    }
    public void update(Game entity){
        gameRepository.update(entity);
    }

    public void delete(Game entity){
        gameRepository.delete(entity);
    }

    public Collection<Game> getAll(){
        return gameRepository
                .getAll()
                .toList();
    }
    public Optional<Game> getById(long id){
        return gameRepository.getById(id);
    }
}
