package ru.javarush.quest.stepanov.questdelta.exception;

public class NoGameFoundException extends RuntimeException{

    public NoGameFoundException() {
        super();
    }

    public NoGameFoundException(String message) {
        super(message);
    }

    public NoGameFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoGameFoundException(Throwable cause) {
        super(cause);
    }
}
