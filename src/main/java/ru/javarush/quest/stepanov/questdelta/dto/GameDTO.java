package ru.javarush.quest.stepanov.questdelta.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.javarush.quest.stepanov.questdelta.entity.GameState;

@ToString
@Getter
@Setter
@Builder(builderMethodName = "with")
public class GameDTO {

    private Long id;
    private QuestDTO quest;
    private UserDTO user;
    private GameState gameState;
    private QuestionDTO lastRedirectedQuestion;

}
