package ru.javarush.quest.stepanov.questdelta.mapper;

import lombok.experimental.UtilityClass;
import ru.javarush.quest.stepanov.questdelta.dto.*;
import ru.javarush.quest.stepanov.questdelta.entity.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public interface Mapper<E extends QuestEntity, R> {
    Optional<R> getDTO(E entity);

    E parse(FormData formData);

    Mapper<User, UserDTO> user = new UserMapper();
    Mapper<Question, QuestionDTO> question = new QuestionMapper();
    Mapper<Answer, AnswerDTO> answer = new AnswerMapper();
    Mapper<Quest, QuestDTO> quest = new QuestMapper();
    Mapper<Game, GameDTO> game = new GameMapper();

    default E fill(E entity, FormData formData){
        Class<? extends QuestEntity> aClass = entity.getClass();
        for (Method method : aClass.getMethods()) {
            String methodName = method.getName();

            if (methodName.startsWith("set")
                    && Modifier.isPublic(method.getModifiers())
                    && method.getParameterCount() == 1
            ){
                String setMethodName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                String classParamName = aClass.getSimpleName().toLowerCase() + "_" + setMethodName;
                String value = formData.getParameter(classParamName);
                if (Objects.nonNull(value)){
                    Class<?> parameterType = method.getParameterTypes()[0];
                    if (ConvertMap.primitiveMap.containsKey(parameterType)) {
                        Object o = ConvertMap.primitiveMap.get(parameterType).apply(value);
                        set(entity, aClass, setMethodName, parameterType, o);
                    } else if (parameterType.isEnum()){
                        for (Object enumConstant : parameterType.getEnumConstants()) {
                            if (enumConstant.toString().equalsIgnoreCase(value)){
                                set(entity, aClass, setMethodName, parameterType, enumConstant);
                                break;
                            }
                        }

                    }
                }
            }
        }
        return entity;
    }

    private static void set(Object entity, Class<? extends QuestEntity> aClass, String setMethodName, Class<?> parameterType, Object o){
        try{
            String setterName = "set" + setMethodName.substring(0, 1).toUpperCase() + setMethodName.substring(1);
            aClass.getMethod(setterName, parameterType).invoke(entity, o);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e){
            throw new RuntimeException(e);
        }
    };


    @UtilityClass
    class ConvertMap{
        public final static Map<Class<?>, Function<String, Object>> primitiveMap = Map.of(
                int.class, Integer::parseInt,
                long.class, Long::parseLong,
                double.class, Double::parseDouble,
                Integer.class, Integer::valueOf,
                Long.class, Long::valueOf,
                Double.class, Double::valueOf,
                String.class, String::toString
        );
    }

}
