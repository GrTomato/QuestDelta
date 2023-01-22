package ru.javarush.quest.stepanov.questdelta.service;

import ru.javarush.quest.stepanov.questdelta.entity.Question;
import ru.javarush.quest.stepanov.questdelta.repository.QuestionRepository;

import java.util.Collection;
import java.util.Optional;

public enum QuestionService {
    INSTANCE;
    private final QuestionRepository questionRepository = QuestionRepository.getInstance();

    private QuestionService() {}

    public void create(Question entity){
        questionRepository.create(entity);
    }
    public void update(Question entity){
        questionRepository.update(entity);
    }

    public void delete(Question entity){
        questionRepository.delete(entity);
    }

    public Collection<Question> getAll(){
        return questionRepository
                .getAll()
                .toList();
    }
    public Optional<Question> getById(long id){
        return questionRepository.getById(id);
    }
}
