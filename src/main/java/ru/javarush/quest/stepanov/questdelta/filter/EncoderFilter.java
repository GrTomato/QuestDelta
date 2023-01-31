package ru.javarush.quest.stepanov.questdelta.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import ru.javarush.quest.stepanov.questdelta.util.URLContainer;

import java.io.IOException;

@WebFilter(value = URLContainer.ALL, initParams = @WebInitParam(name = "code", value = "UTF-8"), filterName = "EncoderFilter")
public class EncoderFilter implements Filter {

    private String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("code");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String characterEncoding = request.getCharacterEncoding();
        if (!code.equalsIgnoreCase(characterEncoding)){
            request.setCharacterEncoding(code);
        }

        characterEncoding = response.getCharacterEncoding();
        if (!code.equalsIgnoreCase(characterEncoding)){
            request.setCharacterEncoding(code);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
