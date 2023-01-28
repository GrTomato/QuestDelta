package ru.javarush.quest.stepanov.questdelta.util;

import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;
import ru.javarush.quest.stepanov.questdelta.dto.UserDTO;

@UtilityClass
public class SessionParser {

    public boolean sessionUserEmpty(HttpSession session){
        return session.getAttribute("user") == null;
    }
    public Object getSessionUser(HttpSession session) {return session.getAttribute("user");}
    public void setSessionUser(HttpSession session, UserDTO user) {session.setAttribute("user", user);}




}
