package hw12_hash_tables.libs.hashing.probing;

public class QuadraticProbing implements IntProbing {

    @Override
    public int probe(int hash, int step, int capacity) {
        int offset = (step * (step + 1)) >>> 1;
        return (hash + offset) & (capacity - 1);
    }
}
