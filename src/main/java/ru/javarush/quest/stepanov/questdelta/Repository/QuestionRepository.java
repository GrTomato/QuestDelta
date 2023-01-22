package ru.javarush.quest.stepanov.questdelta.Repository;

import ru.javarush.quest.stepanov.questdelta.Entity.Question;

import java.util.Collection;
import java.util.stream.Stream;

public class QuestionRepository extends QuestEntityRepository<Question>{

    private static final QuestionRepository INSTANCE = new QuestionRepository();

    private QuestionRepository(){}

    public static QuestionRepository getInstance(){
        return INSTANCE;
    }


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
