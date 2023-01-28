package ru.javarush.quest.stepanov.questdelta.service;

import ru.javarush.quest.stepanov.questdelta.dto.AnswerDTO;
import ru.javarush.quest.stepanov.questdelta.entity.Answer;
import ru.javarush.quest.stepanov.questdelta.mapper.Mapper;
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

    public Collection<AnswerDTO> getAll(){
        return answerRepository
                .getAll()
                .map(Mapper.answer::getDTO)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
    public Optional<AnswerDTO> getById(long id){
        Answer answerEntity = answerRepository.getById(id);
        return Mapper.answer.getDTO(answerEntity);
    }
}
