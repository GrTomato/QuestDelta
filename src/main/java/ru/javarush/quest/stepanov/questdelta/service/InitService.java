package ru.javarush.quest.stepanov.questdelta.service;

import lombok.AllArgsConstructor;
import ru.javarush.quest.stepanov.questdelta.entity.*;
import ru.javarush.quest.stepanov.questdelta.repository.AnswerRepository;
import ru.javarush.quest.stepanov.questdelta.repository.QuestRepository;
import ru.javarush.quest.stepanov.questdelta.repository.QuestionRepository;
import ru.javarush.quest.stepanov.questdelta.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class InitService {

    private final UserRepository userRepository;
    private final QuestRepository questRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository ;

    public void load(){
        this.loadDummyUsers();
        this.loadFirstDummyQuest();
        this.loadSecondDummyQuest();
    }

    private void loadDummyUsers(){
        userRepository.create(
                User.with()
                        .login("admin")
                        .password("admin")
                        .email("admin@gmail.com")
                        .role(UserRole.ADMIN)
                        .build()
        );
        userRepository.create(
                User.with()
                        .login("Anthony")
                        .password("123")
                        .email("anthony@gmail.com")
                        .role(UserRole.PLAYER)
                        .build()
        );
    }

    private void loadFirstDummyQuest(){
        Question question1 = Question.with()
                .text("You wake up in an unknown room. The head hurts and vision is blurry. Suddenly you find a glass of water. What would you do?")
                .gameState(GameState.PROGRESS)
                .build();
        Question question2 = Question.with()
                .text("You drink the water. It was not poisoned. You are feeling better. The door in front of you opens and a zombie walks in. What would you do?")
                .gameState(GameState.PROGRESS)
                .build();
        Question question3 = Question.with()
                .text("You did not drink the water. You died.")
                .gameState(GameState.LOSE)
                .build();
        Question question4 = Question.with()
                .text("You grab a pistol and fired a zombie. The house is safe now! Congratulations, you made it out alive! You won!")
                .gameState(GameState.WIN)
                .build();
        Question question5 = Question.with()
                .text("You tried to broke a window and broke your arm. The pistol is too far and you can not resist a zombie. You died.")
                .gameState(GameState.LOSE)
                .build();

        List.of(question1, question2, question3, question4, question5).forEach(questionRepository::create);

        Answer question1FirstAnswer = Answer.with().text("Drink the water").nextQuestion(question2).build();
        Answer question1SecondAnswer = Answer.with().text("Do not drink the water").nextQuestion(question3).build();
        Answer question2FirstAnswer = Answer.with().text("Grab a pistol and shoot a zombie!").nextQuestion(question4).build();
        Answer question2SecondAnswer = Answer.with().text("Jump out of the window!").nextQuestion(question5).build();
        List.of(question1FirstAnswer, question1SecondAnswer, question2FirstAnswer, question2SecondAnswer).forEach(answerRepository::create);

        List<Answer> firstQuestionAnswers = List.of(question1FirstAnswer, question1SecondAnswer);
        firstQuestionAnswers.forEach(question1.getAnswers()::add);
        List<Answer> secondQuestionAnswers = List.of(question2FirstAnswer, question2SecondAnswer);
        secondQuestionAnswers.forEach(question2.getAnswers()::add);

        Optional<User> questCreator = userRepository.getAll().findFirst();
        if (questCreator.isPresent()){
            questRepository.create(
                    Quest.with()
                            .name("Haunted House")
                            .description("Horror adventure full of zombies and mysteries.")
                            .author(questCreator.get())
                            .startQuestion(question1)
                            .build()
            );
        } else {
            throw new RuntimeException("Could Not Upload dummy quest.");
        }
    }

    private void loadSecondDummyQuest(){

        Question question1 = Question.with()
                .text("You have lost your memory. Do you want to connect with UFO?")
                .gameState(GameState.PROGRESS)
                .build();
        Question question2 = Question.with()
                .text("You have declined UFO call. The adventure is over.")
                .gameState(GameState.LOSE)
                .build();
        Question question3 = Question.with()
                .text("You have accepted UFO call. Do you want to speak to the captain?")
                .gameState(GameState.PROGRESS)
                .build();
        Question question4 = Question.with()
                .text("You have declined an opportunity to speak to the captain of the UFO. The adventure is over.")
                .gameState(GameState.LOSE)
                .build();
        Question question5 = Question.with()
                .text("The alien captain is asking which race do you belong.")
                .gameState(GameState.PROGRESS)
                .build();
        Question question6 = Question.with()
                .text("You have lied the captain and said that you are a robot. But he knows how human beings look like. You have been killed.")
                .gameState(GameState.LOSE)
                .build();
        Question question7 = Question.with()
                .text("You have told the truth. The captain decided to return you home for being honest. You are safe now.")
                .gameState(GameState.WIN)
                .build();

        List.of(question1, question2, question3, question4, question5, question6, question7).forEach(questionRepository::create);

        Answer question1FirstAnswer = Answer.with().text("Accept the UFO call!").nextQuestion(question3).build();
        Answer question1SecondAnswer = Answer.with().text("Decline the UFO call!").nextQuestion(question2).build();
        Answer question3FirstAnswer = Answer.with().text("Yes, I want to meet a captain personally!").nextQuestion(question5).build();
        Answer question3SecondAnswer = Answer.with().text("No, I do not speak to strangers!").nextQuestion(question4).build();
        Answer question5FirstAnswer = Answer.with().text("Tell the truth about human race.").nextQuestion(question7).build();
        Answer question5SecondAnswer = Answer.with().text("These aliens are very suspicious. I'll better lie about being a human. I am ... a robot!").nextQuestion(question6).build();
        List.of(question1FirstAnswer, question1SecondAnswer, question3FirstAnswer, question3SecondAnswer, question5FirstAnswer, question5SecondAnswer).forEach(answerRepository::create);

        List<Answer> firstQuestionAnswers = List.of(question1FirstAnswer, question1SecondAnswer);
        firstQuestionAnswers.forEach(question1.getAnswers()::add);
        List<Answer> thirdQuestionAnswers = List.of(question3FirstAnswer, question3SecondAnswer);
        thirdQuestionAnswers.forEach(question3.getAnswers()::add);
        List<Answer> fifthQuestionAnswers = List.of(question5FirstAnswer, question5SecondAnswer);
        fifthQuestionAnswers.forEach(question5.getAnswers()::add);

        Optional<User> questCreator = userRepository.getAll().findFirst();
        if (questCreator.isPresent()){
            questRepository.create(
                    Quest.with()
                            .name("UFO adventure")
                            .description("Space adventure inspired by JavaRush project tutorial.")
                            .author(questCreator.get())
                            .startQuestion(question1)
                            .build()
            );
        } else {
            throw new RuntimeException("Could Not Upload dummy quest.");
        }

    }

}
