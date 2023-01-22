package ru.javarush.quest.stepanov.questdelta.Repository;

import lombok.Getter;
import ru.javarush.quest.stepanov.questdelta.Entity.QuestEntity;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class QuestEntityRepository<T extends QuestEntity> implements Repository<T>{

    @Getter
    protected Map<Long, T> inMemoryStorage = new HashMap<>();
    private static final AtomicLong counter = new AtomicLong(System.currentTimeMillis());

    @Override
    public Stream<T> getAll() {
        return inMemoryStorage.values().stream();
    }

    @Override
    public abstract Stream<T> find(T entity);

    protected <T, V> boolean isOk(T searchEntity, T user, Function<T, V> fieldGetter) {
        V fieldValue = fieldGetter.apply(user);
        return Objects
                .requireNonNullElse(fieldGetter.apply(searchEntity), fieldValue)
                .equals(fieldValue);
    }


    @Override
    public Optional<T> getById(long id) {
        return Optional.ofNullable(inMemoryStorage.get(id));
    }

    @Override
    public void create(T entity) {
        Long id = counter.getAndIncrement();
        entity.setId(id);
        inMemoryStorage.put(id, entity);
    }

    @Override
    public void update(T entity) {
        Optional<T> user = Optional.ofNullable(inMemoryStorage.get(entity.getId()));
        if (user.isPresent()){
            inMemoryStorage.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(T entity) {
        Optional<T> user = Optional.ofNullable(inMemoryStorage.get(entity.getId()));
        if (user.isPresent()){
            inMemoryStorage.remove(entity.getId());
        }
    }
}
