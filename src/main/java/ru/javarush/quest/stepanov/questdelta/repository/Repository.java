package ru.javarush.quest.stepanov.questdelta.repository;

import java.util.stream.Stream;

public interface Repository<T> {

    Stream<T> getAll();

    Stream<T> find(T entity);

    T getById(long id);

    void create(T entity);
    void update(T entity);
    void delete(T entity);


}
