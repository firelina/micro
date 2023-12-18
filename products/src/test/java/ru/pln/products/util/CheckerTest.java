package ru.pln.products.util;

import org.junit.jupiter.api.Test;
import ru.pln.products.exception.APIException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.pln.products.messages.MessageException.EMPTY_TITLE;
import static ru.pln.products.util.Checker.checkString;

class CheckerTest {
    @Test
    public void whenNullString_thenThrowsAPIException(){
        assertAll(
                () -> {
                    APIException exception = assertThrows(APIException.class, () -> checkString(null));
                    assertThat(exception.getMessage()).isEqualTo(EMPTY_TITLE);
                }
        );
    }
    @Test
    public void whenEmptyString_thenThrowsAPIException(){
        assertAll(
                () -> {
                    APIException exception = assertThrows(APIException.class, () -> checkString(""));
                    assertThat(exception.getMessage()).isEqualTo(EMPTY_TITLE);
                }
        );
    }
    @Test
    public void whenValidString_thenDoesNotThrowAPIException(){
        assertDoesNotThrow(() -> checkString("Some string"));
    }
}