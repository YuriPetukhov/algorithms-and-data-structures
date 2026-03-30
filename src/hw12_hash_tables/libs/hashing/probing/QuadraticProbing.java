package hw12_hash_tables.libs.hashing.probing;

public class QuadraticProbing implements IntProbing {
    @Override
    public int probe(int hash, int step, int capacity) {
        return (hash + step * step) % capacity;
    }
}
