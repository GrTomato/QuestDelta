package ru.javarush.quest.stepanov.questdelta.service;

import lombok.AllArgsConstructor;
import ru.javarush.quest.stepanov.questdelta.dto.QuestDTO;
import ru.javarush.quest.stepanov.questdelta.entity.Quest;
import ru.javarush.quest.stepanov.questdelta.mapper.FormData;
import ru.javarush.quest.stepanov.questdelta.mapper.Mapper;
import ru.javarush.quest.stepanov.questdelta.repository.QuestRepository;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class QuestService {
    private final QuestRepository questRepository;
    public void create(Quest entity){
        questRepository.create(entity);
    }
    public void update(Quest entity){
        questRepository.update(entity);
    }

    public void delete(Quest entity){
        questRepository.delete(entity);
    }

    public Collection<QuestDTO> getAll(){
        return questRepository
                .getAll()
                .map(Mapper.quest::getDTO)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
    public Optional<QuestDTO> getById(long id){
        Quest questEntity = questRepository.getById(id);
        return Mapper.quest.getDTO(questEntity);
    }

    public Optional<QuestDTO> getQuest(FormData formData){
        Quest parsedQuest = Mapper.quest.parse(formData);
        Optional<Quest> foundQuest = questRepository.find(parsedQuest).findFirst();
        return foundQuest.isPresent()
                ? Mapper.quest.getDTO(foundQuest.get())
                : Optional.empty();
    }

}
