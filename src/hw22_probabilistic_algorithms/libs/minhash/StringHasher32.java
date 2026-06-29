package hw22_probabilistic_algorithms.libs.minhash;

@FunctionalInterface
public interface StringHasher32 {

    int hash(String value);
}
