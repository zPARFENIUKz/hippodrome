import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @ExtendWith(MockitoExtension.class)
    public static class MethodsTest {
        private static final String HORSE_NAME = "Horse";
        private static final int HORSE_SPEED = 5;
        private static final int HORSE_DISTANCE = 6;
        private static Horse twoArgumentsHorse = Mockito.spy(new Horse(HORSE_NAME, HORSE_SPEED));
        private static Horse threeArgumentsHorse = Mockito.spy(new Horse(HORSE_NAME, HORSE_SPEED, HORSE_DISTANCE));

        @BeforeEach
        void reInitHorses() {
            twoArgumentsHorse = Mockito.spy(new Horse(HORSE_NAME, HORSE_SPEED));
            threeArgumentsHorse = Mockito.spy(new Horse(HORSE_NAME, HORSE_SPEED, HORSE_DISTANCE));
        }

        @Test
        void getNameTest() {
            Assertions.assertEquals(HORSE_NAME, twoArgumentsHorse.getName());
            Assertions.assertEquals(HORSE_NAME, threeArgumentsHorse.getName());
        }

        @Test
        void getSpeedTest() {
            Assertions.assertEquals(HORSE_SPEED, twoArgumentsHorse.getSpeed());
            Assertions.assertEquals(HORSE_SPEED, threeArgumentsHorse.getSpeed());
        }

        @Test
        void getDistanceTest() {
            Assertions.assertEquals(HORSE_DISTANCE, threeArgumentsHorse.getDistance());
            Assertions.assertEquals(0, twoArgumentsHorse.getDistance());
        }

        @Test
        void moveTest() {
            try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
                twoArgumentsHorse.move();
                horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            }
        }

        @ParameterizedTest
        @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9})
        void distanceFormulaTest(double getRandomDoubleResult) {
            try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
                horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(getRandomDoubleResult);
                final double expectedDistance = twoArgumentsHorse.getDistance() + twoArgumentsHorse.getSpeed() * getRandomDoubleResult;
                twoArgumentsHorse.move();
                Assertions.assertEquals(expectedDistance, twoArgumentsHorse.getDistance());
            }
        }

    }
}