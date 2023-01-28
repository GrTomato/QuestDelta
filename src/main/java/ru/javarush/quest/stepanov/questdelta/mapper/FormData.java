package ru.javarush.quest.stepanov.questdelta.mapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.ToString;

import java.util.Arrays;
import java.util.Map;

public class FormData {

    private final Map<String, String[]> parameterMap;

    private FormData(HttpServletRequest request) {
        this.parameterMap = request.getParameterMap();
    }

    public static FormData of(HttpServletRequest request){
        return new FormData(request);
    }

    public String getParameter(String parameterName){
        return parameterMap.getOrDefault(parameterName, new String[1])[0];
    }
    public Long getId(){
        return parameterMap.containsKey("id")
                ? Long.valueOf(getParameter("id"))
                : null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
            stringBuilder.append(stringEntry.getKey()).append(" : ").append(Arrays.toString(stringEntry.getValue()));
        }
        return stringBuilder.toString();
    }
}
