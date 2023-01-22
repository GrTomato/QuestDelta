package ru.javarush.quest.stepanov.questdelta.service;

import ru.javarush.quest.stepanov.questdelta.entity.Quest;
import ru.javarush.quest.stepanov.questdelta.repository.QuestRepository;

import java.util.Collection;
import java.util.Optional;

public enum QuestService {
    INSTANCE;
    private final QuestRepository questRepository = QuestRepository.getInstance();

    private QuestService() {}

    public void create(Quest entity){
        questRepository.create(entity);
    }
    public void update(Quest entity){
        questRepository.update(entity);
    }

    public void delete(Quest entity){
        questRepository.delete(entity);
    }

    public Collection<Quest> getAll(){
        return questRepository
                .getAll()
                .toList();
    }
    public Optional<Quest> getById(long id){
        return questRepository.getById(id);
    }

}
