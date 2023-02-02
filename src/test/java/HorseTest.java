import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class HorseTest {
    public static class ConstructorTest {
        protected static final String CONSTRUCTOR_NULL_INIT_EXCEPTION_MESSAGE = "Name cannot be null.";
        protected static final String CONSTRUCTOR_WHITESPACE_CHARACTERS_NAME_EXCEPTION_MESSAGE = "Name cannot be blank.";
        protected static final String CONSTRUCTOR_NEGATIVE_SPEED_EXCEPTION_MESSAGE = "Speed cannot be negative.";
        protected static final String CONSTRUCTOR_NEGATIVE_DISTANCE_EXCEPTION_MESSAGE =  "Distance cannot be negative.";


        @Test
        void nullConstructorTest() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1));
        }

        @Test
        void nullConstructorMessageTest() {
            try {
                new Horse(null, 1);
            } catch (Exception e) {
                Assertions.assertEquals(CONSTRUCTOR_NULL_INIT_EXCEPTION_MESSAGE, e.getMessage());
            }
        }

        @ParameterizedTest
        @MethodSource("whitespaceNameProviderFactory")
        void whiteSpaceCharactersNameTest(String name) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1));
        }

        @ParameterizedTest
        @MethodSource("whitespaceNameProviderFactory")
        void whiteSpaceCharactersNameExceptionMessageTest(String name) {
            try {
                new Horse(name, 1);
            } catch (Exception e) {
                Assertions.assertEquals(CONSTRUCTOR_WHITESPACE_CHARACTERS_NAME_EXCEPTION_MESSAGE, e.getMessage());
            }
        }

        static Stream<String> whitespaceNameProviderFactory() {
            return Stream.of("\n", "\t", "\r", " ", " \n\t\r ");
        }

        @Test
        void negativeSpeedConstructorTest() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("horse", -1));
        }

        @Test
        void negativeSpeedConstructorExceptionMessageTest() {
            try {
                new Horse("horse", -1);
            } catch (Exception e) {
                Assertions.assertEquals(CONSTRUCTOR_NEGATIVE_SPEED_EXCEPTION_MESSAGE, e.getMessage());
            }
        }

        @Test
        void negativeDistanceConstructorTest() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("horse", 1, -1));
        }

        @Test
        void negativeDistanceConstructorExceptionMessageTest() {
            try {
                new Horse("horse", 1, -1);
            } catch (Exception e) {
                Assertions.assertEquals(CONSTRUCTOR_NEGATIVE_DISTANCE_EXCEPTION_MESSAGE, e.getMessage());
            }
        }
    }

    public static class MethodsTest {

    }
}