package ru.javarush.quest.stepanov.questdelta.mapper;

import ru.javarush.quest.stepanov.questdelta.dto.AnswerDTO;
import ru.javarush.quest.stepanov.questdelta.dto.QuestionDTO;
import ru.javarush.quest.stepanov.questdelta.entity.Answer;
import ru.javarush.quest.stepanov.questdelta.entity.Question;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class QuestionMapper implements Mapper<Question, QuestionDTO>{


    @Override
    public Optional<QuestionDTO> getDTO(Question questionEntity) {

        List<AnswerDTO> answerDTOs = questionEntity.getAnswers().stream()
                .map(Mapper.answer::getDTO)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        return questionEntity != null
                ? Optional.ofNullable(
                        QuestionDTO
                                .with()
                                .id(questionEntity.getId())
                                .text(questionEntity.getText())
                                .answers(answerDTOs)
                                .build())
                : Optional.empty();
    }

    @Override
    public Question parse(FormData formData) {
        Question parsedQuestion = Question.with().build();
        return fill(parsedQuestion, formData);
    }
}
