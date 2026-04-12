package hw12_hash_tables.libs.hashing.tables;

import hw12_hash_tables.libs.hashing.IntHashTable;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable implements IntHashTable {

    private static final double MAX_LOAD_FACTOR = 2.0;

    private List<Integer>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public SeparateChainingHashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        table = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    @Override
    public void insert(int x) {
        int index = hash(x);

        if (table[index].contains(x)) {
            return;
        }

        table[index].add(x);
        size++;

        if ((double) size / table.length > MAX_LOAD_FACTOR) {
            resize(table.length * 2);
        }
    }

    @Override
    public boolean search(int x) {
        return table[hash(x)].contains(x);
    }

    @Override
    public void remove(int x) {
        if (table[hash(x)].remove(Integer.valueOf(x))) {
            size--;
        }
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        List<Integer>[] oldTable = table;

        table = new List[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            table[i] = new LinkedList<>();
        }

        for (List<Integer> bucket : oldTable) {
            for (int value : bucket) {
                table[hash(value)].add(value);
            }
        }
    }

    private int hash(int x) {
        return Math.floorMod(x, table.length);
    }
}