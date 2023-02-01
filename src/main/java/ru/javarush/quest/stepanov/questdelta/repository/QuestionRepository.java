package ru.javarush.quest.stepanov.questdelta.repository;

import ru.javarush.quest.stepanov.questdelta.entity.Question;

import java.util.stream.Stream;

public class QuestionRepository extends QuestEntityRepository<Question>{

    @Override
    public Stream<Question> find(Question entity) {
        return inMemoryStorage.values().stream()
                .filter(question ->
                                isOk(entity, question, Question::getId) &&
                                isOk(entity, question, Question::getText) &&
                                isOk(entity, question, Question::getAnswers)
                );
    }
}
