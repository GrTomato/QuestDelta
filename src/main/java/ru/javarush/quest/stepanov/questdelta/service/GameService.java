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


    public Optional<GameDTO> updateGameProgress(FormData formData){
        Question nextQuestion = Mapper.question.parse(formData);
        Optional<Question> findQuestion = questionRepository.find(nextQuestion).findFirst();
        if (findQuestion.isPresent()){
            Game currentGame = Mapper.game.parse(formData);
            Optional<Game> currentGameEntity = gameRepository.find(currentGame).findFirst();

            if (currentGameEntity.isPresent()){
                Game game = currentGameEntity.get();
                game.setLastRedirectedQuestion(findQuestion.get());
                gameRepository.update(game);
                return Mapper.game.getDTO(game);
            } else {
                throw new RuntimeException("No mathces for current game was found, please investigate this bug.");
            }

        } else {
            throw new RuntimeException("No mathces for next question was found. Please, investigate.");
        }
    }

    private boolean restartGame(FormData formData){
        return formData.getParameter("instruct") != null && formData.getParameter("instruct").equalsIgnoreCase("restart");
    }

    private Game resetGameProgress(Game currentGame){
        Question startQuestion = currentGame.getQuest().getStartQuestion();
        currentGame.setLastRedirectedQuestion(startQuestion);
        gameRepository.update(currentGame);
        return currentGame;
    }
    public Optional<GameDTO> getGame(FormData formData, Long userId) {

        Quest questParsed = Mapper.quest.parse(formData);
        Quest existingQuest = questRepository.getById(questParsed.getId());
        Game parsedGame = Mapper.game.parse(formData);
        parsedGame.setQuest(existingQuest);
        User gameUser = userRepository.getById(userId);
        parsedGame.setUser(gameUser);
        parsedGame.setGameState(GameState.PROGRESS);

        Optional<Game> foundGame = gameRepository.find(parsedGame).findFirst();
        if (foundGame.isPresent()){
            if (this.restartGame(formData)){
                return Mapper.game.getDTO(
                        this.resetGameProgress(foundGame.get()));
            } else {
                return Mapper.game.getDTO(foundGame.get());
            }
        } else {
            Game newGame = createNewGame(existingQuest.getId(), userId);
            return Mapper.game.getDTO(newGame);
        }
    }

    private Game createNewGame(Long questId, Long userId){

        Quest quest = questRepository.getById(questId);
        User user = userRepository.getById(userId);

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
    public Optional<GameDTO> getById(long id){
        Game gameEntity = gameRepository.getById(id);
        return Mapper.game.getDTO(gameEntity);
    }
}
