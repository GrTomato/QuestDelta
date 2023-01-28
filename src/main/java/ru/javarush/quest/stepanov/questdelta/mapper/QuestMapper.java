package ru.javarush.quest.stepanov.questdelta.mapper;

import ru.javarush.quest.stepanov.questdelta.dto.QuestDTO;
import ru.javarush.quest.stepanov.questdelta.dto.QuestionDTO;
import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;
import ru.javarush.quest.stepanov.questdelta.entity.Quest;

import java.util.Optional;

public class QuestMapper implements Mapper<Quest, QuestDTO> {
    @Override
    public Optional<QuestDTO> getDTO(Quest questEntity) {

        Optional<UserDTO> authorDTO = Mapper.user.getDTO(questEntity.getAuthor());
        Optional<QuestionDTO> startQuestionDTO = Mapper.question.getDTO(questEntity.getStartQuestion());

        return questEntity != null
                ? Optional.ofNullable(
                        QuestDTO
                                .with()
                                .id(questEntity.getId())
                                .author(authorDTO.orElse(null))
                                .description(questEntity.getDescription())
                                .name(questEntity.getName())
                                .startQuestion(startQuestionDTO.orElse(null))
                                .build())
                : Optional.empty();
    }

    @Override
    public Quest parse(FormData formData) {
        Quest parsedQuestion = Quest.with().build();
        return fill(parsedQuestion, formData);
    }
}
