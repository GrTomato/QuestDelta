package ru.javarush.quest.stepanov.questdelta.service;

import ru.javarush.quest.stepanov.questdelta.dto.GameDTO;
import ru.javarush.quest.stepanov.questdelta.entity.*;
import ru.javarush.quest.stepanov.questdelta.mapper.FormData;
import ru.javarush.quest.stepanov.questdelta.mapper.Mapper;
import ru.javarush.quest.stepanov.questdelta.repository.GameRepository;
import ru.javarush.quest.stepanov.questdelta.repository.QuestRepository;
import ru.javarush.quest.stepanov.questdelta.repository.QuestionRepository;
import ru.javarush.quest.stepanov.questdelta.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

public enum GameService{

    INSTANCE;
    private final GameRepository gameRepository = GameRepository.getInstance();
    private final UserRepository userRepository = UserRepository.getInstance();
    private final QuestRepository questRepository = QuestRepository.getInstance();
    private final QuestionRepository questionRepository = QuestionRepository.getInstance();

    private GameService() {}


    public Optional<GameDTO> updateGameProgress(Long gameId, Long newLastQuestion){
        Game currentGame = gameRepository.getById(gameId);
        currentGame.setLastRedirectedQuestion(questionRepository.getById(newLastQuestion));

        if (currentGame.getLastRedirectedQuestion().getGameState() != GameState.PROGRESS){
            currentGame.setGameState(currentGame.getLastRedirectedQuestion().getGameState());
        }

        gameRepository.update(currentGame);
        return Mapper.game.getDTO(currentGame);
    }

    private boolean restartGame(FormData formData){
        return formData.getParameter("instruct") != null && formData.getParameter("instruct").equalsIgnoreCase("restart");
    }

    private Game restartGameProgress(Game currentGame){
        Question startQuestion = currentGame.getQuest().getStartQuestion();
        currentGame.setLastRedirectedQuestion(startQuestion);
        gameRepository.update(currentGame);
        return currentGame;
    }
    public Optional<GameDTO> getGame(FormData formData, Long userId, Long questId) {

        Quest existingQuest = questRepository.getById(questId);
        User gameUser = userRepository.getById(userId);

        Game parsedGame = Mapper.game.parse(formData);
        parsedGame.setQuest(existingQuest);
        parsedGame.setUser(gameUser);
        parsedGame.setGameState(GameState.PROGRESS);

        Optional<Game> foundGame = gameRepository.find(parsedGame).findFirst();

        if (foundGame.isPresent()){
            if (restartGame(formData)){
                return Mapper.game.getDTO(
                        restartGameProgress(foundGame.get())
                );
            } else {
                return Mapper.game.getDTO(foundGame.get());
            }
        } else {
            return Optional.empty();
        }
    }

    public Optional<GameDTO> createNewGame(Long questId, Long userId){

        Quest quest = questRepository.getById(questId);
        User user = userRepository.getById(userId);

        Game newGame = Game.with()
                .quest(quest)
                .gameState(GameState.PROGRESS)
                .user(user)
                .lastRedirectedQuestion(quest.getStartQuestion())
                .build();
        gameRepository.create(newGame);
        return Mapper.game.getDTO(newGame);
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

    public Collection<GameDTO> getAllByUser(Long userId){
        User user = userRepository.getById(userId);
        return gameRepository
                .getAll()
                .filter(game -> game.getUser().equals(user))
                .map(Mapper.game::getDTO)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
    public Optional<GameDTO> getById(long id){
        Game gameEntity = gameRepository.getById(id);
        return Mapper.game.getDTO(gameEntity);
    }
}
