package ru.javarush.quest.stepanov.questdelta.Repository;

import ru.javarush.quest.stepanov.questdelta.Entity.Quest;

import java.util.stream.Stream;

public class QuestRepository extends QuestEntityRepository<Quest> {

    private static final QuestRepository INSTANCE = new QuestRepository();

    private QuestRepository(){}

    public static QuestRepository getInstance(){
        return INSTANCE;
    }

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
