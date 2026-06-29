package hw22_probabilistic_algorithms.libs.minhash;

import java.util.Set;

@FunctionalInterface
public interface ShingleSetExtractor {

    Set<String> extract(String text);
}
