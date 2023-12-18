package ru.pln.products.util;

import ru.pln.products.exception.APIException;

import java.util.Objects;

import static ru.pln.products.messages.MessageException.EMPTY_TITLE;

public class Checker {

    public static void checkString(String string){
        checkString(string, EMPTY_TITLE);
    }

    public static void checkString(String string, String exceptionMessage){
        if (isEmptyString(string)){
            throw new APIException(exceptionMessage);
        }
    }
    public static boolean isEmptyString(String str){
        return Objects.isNull(str) || str.trim().length() == 0;
    }
}
