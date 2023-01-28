package ru.javarush.quest.stepanov.questdelta.mapper;

import ru.javarush.quest.stepanov.questdelta.dto.AnswerDTO;
import ru.javarush.quest.stepanov.questdelta.dto.QuestionDTO;
import ru.javarush.quest.stepanov.questdelta.entity.Answer;

import java.util.Optional;

public class AnswerMapper implements Mapper<Answer, AnswerDTO> {
    @Override
    public Optional<AnswerDTO> getDTO(Answer answerEntity) {
        Optional<QuestionDTO> questionDTO = Mapper.question.getDTO(answerEntity.getNextQuestion());
        return answerEntity != null
                ? Optional.ofNullable(
                        AnswerDTO
                                .with()
                                .id(answerEntity.getId())
                                .text(answerEntity.getText())
                                .nextQuestion(questionDTO.orElse(null))
                                .build())
                : Optional.empty();
    }

    @Override
    public Answer parse(FormData formData) {
        Answer parsedAnswer = Answer.with().build();
        return fill(parsedAnswer, formData);
    }
}
