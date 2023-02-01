package ru.javarush.quest.stepanov.questdelta.service;

import lombok.AllArgsConstructor;
import ru.javarush.quest.stepanov.questdelta.dto.QuestionDTO;
import ru.javarush.quest.stepanov.questdelta.entity.Question;
import ru.javarush.quest.stepanov.questdelta.mapper.FormData;
import ru.javarush.quest.stepanov.questdelta.mapper.Mapper;
import ru.javarush.quest.stepanov.questdelta.repository.QuestionRepository;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public void create(Question entity){
        questionRepository.create(entity);
    }
    public void update(Question entity){
        questionRepository.update(entity);
    }

    public void delete(Question entity){
        questionRepository.delete(entity);
    }

    public Collection<QuestionDTO> getAll(){
        return questionRepository
                .getAll()
                .map(Mapper.question::getDTO)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
    public Optional<QuestionDTO> getById(long id){
        Question questionEntity = questionRepository.getById(id);
        return Mapper.question.getDTO(questionEntity);
    }

    public Optional<QuestionDTO> getQuestion(FormData formData){
        Question parsedQuestion = Mapper.question.parse(formData);
        Optional<Question> question = questionRepository.find(parsedQuestion).findFirst();
        return question.isPresent()
                ? Mapper.question.getDTO(question.get())
                : Optional.empty();
    }
}
