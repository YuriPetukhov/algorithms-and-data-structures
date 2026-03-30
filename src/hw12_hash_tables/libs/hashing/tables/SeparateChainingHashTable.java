package hw12_hash_tables.libs.hashing.tables;

import hw12_hash_tables.libs.hashing.IntHashTable;

import java.util.LinkedList;
import java.util.List;

public class SeparateChainingHashTable implements IntHashTable {
    private final List<Integer>[] table;

    @SuppressWarnings("unchecked")
    public SeparateChainingHashTable(int capacity) {
        this.table = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    @Override
    public void insert(int x) {
        int index = hash(x);
        if (!table[index].contains(x)) {
            table[index].add(x);
        }
    }

    @Override
    public boolean search(int x) {
        int index = hash(x);
        return table[index].contains(x);
    }

    @Override
    public void remove(int x) {
        int index = hash(x);
        table[index].remove(Integer.valueOf(x));
    }

    private int hash(int x) {
        return Math.floorMod(x, table.length);
    }
}