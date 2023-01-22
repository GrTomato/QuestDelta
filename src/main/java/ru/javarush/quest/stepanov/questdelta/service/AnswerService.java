package ru.javarush.quest.stepanov.questdelta.service;

import ru.javarush.quest.stepanov.questdelta.entity.Answer;
import ru.javarush.quest.stepanov.questdelta.repository.AnswerRepository;

import java.util.Collection;
import java.util.Optional;

public enum AnswerService {

    INSTANCE;
    private final AnswerRepository answerRepository = AnswerRepository.getInstance();

    public void create(Answer entity){
        answerRepository.create(entity);
    }
    public void update(Answer entity){
        answerRepository.update(entity);
    }

    public void delete(Answer entity){
        answerRepository.delete(entity);
    }

    public Collection<Answer> getAll(){
        return answerRepository
                .getAll()
                .toList();
    }
    public Optional<Answer> getById(long id){
        return answerRepository.getById(id);
    }
}
