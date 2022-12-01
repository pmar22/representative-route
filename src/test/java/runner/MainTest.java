package runner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {

    @Test
    void whenArgumentsNotProvidedThenThrowException() {
        assertThrows(RuntimeException.class, () -> Main.main(new String[0]));
    }
}