package ru.javarush.quest.stepanov.questdelta.repository;

import ru.javarush.quest.stepanov.questdelta.entity.Answer;

import java.util.Comparator;
import java.util.stream.Stream;

public class AnswerRepository extends QuestEntityRepository<Answer>{

    private static final AnswerRepository INSTANCE = new AnswerRepository();

    private AnswerRepository(){}

    public static AnswerRepository getInstance(){
        return INSTANCE;
    }

    @Override
    public Stream<Answer> find(Answer entity) {
        return inMemoryStorage.values().stream()
                .filter(answer ->
                        isOk(entity, answer, Answer::getId) &&
                                isOk(entity, answer, Answer::getText) &&
                                isOk(entity, answer, Answer::getNextQuestion)
                )
                .sorted(Comparator.comparing(Answer::getId));
    }
}
