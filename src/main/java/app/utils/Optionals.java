package app.utils;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public class Optionals {
    public static <T> Optional<T> or(Supplier<Optional<T>>... optionals) {
        return Arrays.stream(optionals)
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .findFirst()
                .orElseGet(Optional::empty);
    }
}
