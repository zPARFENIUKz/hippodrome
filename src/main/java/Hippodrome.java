import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

public class Hippodrome {
    protected static final Logger logger = LoggerFactory.getLogger(Hippodrome.class);
    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            logger.error("Horses list is null");
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            logger.error("Horses list is empty");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }
        this.horses = horses;
        logger.debug(String.format("Создание Hippodrome, лошадей [%d]", horses.size()));
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
