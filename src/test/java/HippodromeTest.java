import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public static class MethodsTests {
        private List<Horse> createUniqueHorses(int count) {
            List<Horse> horses = new ArrayList<>(count);
            IntStream.rangeClosed(0, count)
                    .boxed()
                    .map(index -> new Horse(String.valueOf(index), index))
                    .forEach(horses::add);
            return horses;
        }

        @Test
        void getHorsesTest() {
            final List<Horse> horses = createUniqueHorses(30);
            Assertions.assertEquals(horses, new Hippodrome(horses).getHorses());
        }

        @Test
        void moveTest() {

            final List<Horse> horses = IntStream.rangeClosed(1, 50)
                    .boxed()
                    .map(i -> Mockito.mock(Horse.class))
                    .collect(Collectors.toList());
            final Hippodrome hippodrome = new Hippodrome(horses);
            hippodrome.move();
            horses.forEach(horse -> Mockito.verify(horse).move());
        }

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        @Test
        void getWinnerTest() {
            final List<Horse> horses = createUniqueHorses(50);
            final Hippodrome hippodrome = new Hippodrome(horses);
            final Horse winner = hippodrome.getHorses().stream()
                    .max(Comparator.comparing(Horse::getDistance)).get();
            assertEquals(winner, hippodrome.getWinner());
        }
    }
}