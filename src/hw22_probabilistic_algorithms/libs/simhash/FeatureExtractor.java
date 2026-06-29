package hw22_probabilistic_algorithms.libs.simhash;

import java.util.Map;

@FunctionalInterface
public interface FeatureExtractor {

    Map<String, Integer> extract(String text);
}
