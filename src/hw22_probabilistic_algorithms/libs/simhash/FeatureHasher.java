package hw22_probabilistic_algorithms.libs.simhash;

@FunctionalInterface
public interface FeatureHasher {

    long hash(String feature);
}
