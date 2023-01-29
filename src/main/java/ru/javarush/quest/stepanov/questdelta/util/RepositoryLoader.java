package ru.javarush.quest.stepanov.questdelta.util;

import lombok.experimental.UtilityClass;
import ru.javarush.quest.stepanov.questdelta.entity.*;
import ru.javarush.quest.stepanov.questdelta.repository.AnswerRepository;
import ru.javarush.quest.stepanov.questdelta.repository.QuestRepository;
import ru.javarush.quest.stepanov.questdelta.repository.QuestionRepository;
import ru.javarush.quest.stepanov.questdelta.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class RepositoryLoader {

    private final UserRepository userRepository = UserRepository.getInstance();
    private final QuestRepository questRepository = QuestRepository.getInstance();
    private final QuestionRepository questionRepository = QuestionRepository.getInstance();
    private final AnswerRepository answerRepository = AnswerRepository.getInstance();

    public static void load(){
        loadDefaultData();
    }

    private static void loadDefaultData(){

        userRepository.create(
                User.with()
                        .login("Anthony")
                        .password("123")
                        .email("anthony@gmail.com")
                        .role(UserRole.PLAYER)
                        .build()
        );
        userRepository.create(
                User.with()
                        .login("Beth")
                        .password("qwerty")
                        .email("beth@gmail.com")
                        .role(UserRole.PLAYER)
                        .build()
        );

        User jamesUser = User.with()
                .login("James")
                .password("duisgfd")
                .email("james@gmail.com")
                .role(UserRole.PLAYER)
                .build();
        userRepository.create(jamesUser);


        Answer firstAnswer = Answer.with().text("Drink the water").build();
        Answer secondAnswer = Answer.with().text("Do not drink the water").build();
        answerRepository.create(firstAnswer);
        answerRepository.create(secondAnswer);

        List<Answer> firstQuestionAnswers = new ArrayList<>();
        firstQuestionAnswers.add(firstAnswer);
        firstQuestionAnswers.add(secondAnswer);

        Question firstQuestion = Question.with()
                .text("You wake up in an unknown room. The head hurts and vision is blurry. Suddenly you find a glass of water. What would you do?")
                .gameState(GameState.PROGRESS)
                // .answers(firstQuestionAnswers)
                .build();
        firstQuestionAnswers.forEach(firstQuestion.getAnswers()::add);

        Question secondQuestion = Question.with()
                .text("You drunk the water. It was not poisoned. You feel better. The door in front of you opens and a zombie walks in. What would you do?")
                .gameState(GameState.PROGRESS)
                // .answers(new ArrayList<Answer>())
                .build();

        Question thirdQuestion = Question.with()
                .text("You did not drink the water. You died.")
                .gameState(GameState.LOSE)
                .build();

        firstAnswer.setNextQuestion(secondQuestion);
        secondAnswer.setNextQuestion(thirdQuestion);

        questionRepository.create(firstQuestion);
        questionRepository.create(secondQuestion);
        questionRepository.create(thirdQuestion);


        User dummyUser = userRepository.getById(jamesUser.getId());
        questRepository.create(
                Quest.with()
                        .name("Haunted House")
                        .description("Horror adventure in style of early 80s horror films.")
                        .author(dummyUser)
                        .startQuestion(firstQuestion)
                        .build()
        );

        questRepository.create(
                Quest.with()
                        .name("Deadly sunset")
                        .description("Adventure quest inspired by classical road movies.")
                        .author(dummyUser)
                        .startQuestion(Question.with().text("You wake up in an unknown place.").build())
                        .build()
        );

    }


}
