package ru.pln.users.util;

import ru.pln.users.exception.APIException;

import java.util.Objects;

import static ru.pln.users.messages.MessageException.EMPTY_TITLE;

public class Checker {
    public static void checkString(String string){
        checkString(string, EMPTY_TITLE);
    }

    public static<N extends Number> void checkNumber(N number, String exceptionMessage){
        if (Objects.isNull(number)){
            throw new APIException(exceptionMessage);
        }
    }

    public static void checkString(String string, String exceptionMessage){
        if (Objects.isNull(string) || string.trim().length() == 0){
            throw new APIException(exceptionMessage);
        }
    }
    public static <T> void checkObject(T object, String exceptionMessage){
        if(Objects.isNull(object))
            throw new APIException(exceptionMessage);
    }
    public static boolean isEmptyString(String str){
        return Objects.isNull(str) || str.trim().length() == 0;
    }
}
