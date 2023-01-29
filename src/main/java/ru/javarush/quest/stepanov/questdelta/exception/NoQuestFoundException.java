package ru.javarush.quest.stepanov.questdelta.exception;

public class NoQuestFoundException extends RuntimeException{

    public NoQuestFoundException() {
        super();
    }

    public NoQuestFoundException(String message) {
        super(message);
    }

    public NoQuestFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoQuestFoundException(Throwable cause) {
        super(cause);
    }
}
