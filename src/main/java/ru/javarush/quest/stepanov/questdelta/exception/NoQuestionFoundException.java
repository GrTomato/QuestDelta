package ru.javarush.quest.stepanov.questdelta.exception;

public class NoQuestionFoundException extends RuntimeException{

    public NoQuestionFoundException() {
        super();
    }

    public NoQuestionFoundException(String message) {
        super(message);
    }

    public NoQuestionFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoQuestionFoundException(Throwable cause) {
        super(cause);
    }
}
