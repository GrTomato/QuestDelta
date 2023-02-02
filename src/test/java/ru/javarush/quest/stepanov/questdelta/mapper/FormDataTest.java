package ru.javarush.quest.stepanov.questdelta.mapper;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FormDataTest {

    @ParameterizedTest
    @CsvSource({
            "user_id,111",
            "user_login,juan",
            "game_id,555",
            "answer_text,dummyAnswerText"
    })
    @DisplayName("Check whether form data set and get data correctly.")
    void FormDataGetParameterTest(String key, String value) {

        Map<String, String[]> fakeRequestParamMap = new HashMap<>();
        fakeRequestParamMap.put(key, new String[]{value});

        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        Mockito.when(req.getParameterMap()).thenReturn(fakeRequestParamMap);
        FormData formData = FormData.of(req);

        assertEquals(formData.getParameter(key), value);
    }
}