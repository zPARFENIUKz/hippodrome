import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HippodromeTest {
    public static class ConstructorTests {
        private static final String NULL_ARG_CONSTRUCTOR_ERROR_MESSAGE = "Horses cannot be null.";
        private static final String EMPTY_LIST_EXCEPTION_CONSTRUCTOR_MESSAGE = "Horses cannot be empty.";

        @Test
        void nullArgExceptionConstructorTest() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        }

        @Test
        void nullArgErrorMessageConstructorTest() {
            try {
                new Hippodrome(null);
            } catch (Exception e) {
                Assertions.assertEquals(NULL_ARG_CONSTRUCTOR_ERROR_MESSAGE, e.getMessage());
            }
        }

        @Test
        void emptyListExceptionConstructorTest() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
        }

        @Test
        void emptyListErrorMessageConstructorTest() {
            try {
                new Hippodrome(Collections.emptyList());
            } catch (Exception e) {
                assertEquals(EMPTY_LIST_EXCEPTION_CONSTRUCTOR_MESSAGE, e.getMessage());
            }
        }
    }

}