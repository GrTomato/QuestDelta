package ru.javarush.quest.stepanov.questdelta.repository;

import ru.javarush.quest.stepanov.questdelta.entity.Quest;

import java.util.stream.Stream;

public class QuestRepository extends QuestEntityRepository<Quest> {

    @Override
    public Stream<Quest> find(Quest entity) {
        return inMemoryStorage.values().stream()
                .filter(quest ->
                    isOk(entity, quest, Quest::getId) &&
                            isOk(entity, quest, Quest::getName) &&
                            isOk(entity, quest, Quest::getAuthor) &&
                            isOk(entity, quest, Quest::getDescription) &&
                            isOk(entity, quest, Quest::getStartQuestion)
                );
    }
}
