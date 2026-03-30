package hw12_hash_tables.libs.hashing.tables;

import hw12_hash_tables.libs.hashing.IntHashTable;
import hw12_hash_tables.libs.hashing.probing.IntProbing;

public class OpenAddressingHashTable implements IntHashTable {
    private enum State {
        EMPTY,
        OCCUPIED,
        DELETED
    }

    private final int[] table;
    private final State[] states;
    private final IntProbing probing;

    public OpenAddressingHashTable(int capacity, IntProbing probing) {
        this.table = new int[capacity];
        this.states = new State[capacity];
        this.probing = probing;

        for (int i = 0; i < capacity; i++) {
            states[i] = State.EMPTY;
        }
    }

    @Override
    public void insert(int x) {
        int hash = hash(x);
        int firstDeleted = -1;

        for (int step = 0; step < table.length; step++) {
            int index = probing.probe(hash, step, table.length);

            if (states[index] == State.OCCUPIED && table[index] == x) {
                return;
            }

            if (states[index] == State.DELETED && firstDeleted == -1) {
                firstDeleted = index;
            }

            if (states[index] == State.EMPTY) {
                if (firstDeleted != -1) {
                    table[firstDeleted] = x;
                    states[firstDeleted] = State.OCCUPIED;
                } else {
                    table[index] = x;
                    states[index] = State.OCCUPIED;
                }
                return;
            }
        }

        if (firstDeleted != -1) {
            table[firstDeleted] = x;
            states[firstDeleted] = State.OCCUPIED;
            return;
        }

        throw new IllegalStateException("Hash table is full");
    }

    @Override
    public boolean search(int x) {
        int hash = hash(x);

        for (int step = 0; step < table.length; step++) {
            int index = probing.probe(hash, step, table.length);

            if (states[index] == State.EMPTY) {
                return false;
            }

            if (states[index] == State.OCCUPIED && table[index] == x) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void remove(int x) {
        int hash = hash(x);

        for (int step = 0; step < table.length; step++) {
            int index = probing.probe(hash, step, table.length);

            if (states[index] == State.EMPTY) {
                return;
            }

            if (states[index] == State.OCCUPIED && table[index] == x) {
                states[index] = State.DELETED;
                return;
            }
        }
    }

    private int hash(int x) {
        return Math.floorMod(x, table.length);
    }
}