package ru.javarush.quest.stepanov.questdelta.exception;

public class NoValidSessionException extends RuntimeException{

    public NoValidSessionException() {
        super();
    }

    public NoValidSessionException(String message) {
        super(message);
    }

    public NoValidSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoValidSessionException(Throwable cause) {
        super(cause);
    }
}
