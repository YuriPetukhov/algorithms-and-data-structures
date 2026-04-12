package hw12_hash_tables.libs.hashing.tables;

import hw12_hash_tables.libs.hashing.IntHashTable;
import hw12_hash_tables.libs.hashing.probing.IntProbing;

import java.util.Arrays;

public class OpenAddressingHashTable implements IntHashTable {

    private enum State {
        EMPTY,
        OCCUPIED,
        DELETED
    }

    private static final double MAX_LOAD_FACTOR = 0.75;

    private int[] table;
    private State[] states;
    private final IntProbing probing;

    private int size;
    private int deletedCount;

    public OpenAddressingHashTable(int capacity, IntProbing probing) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        this.table = new int[nextPowerOfTwo(capacity)];
        this.states = new State[this.table.length];
        this.probing = probing;

        Arrays.fill(states, State.EMPTY);
    }

    @Override
    public void insert(int x) {
        if ((size + deletedCount + 1.0) / table.length > MAX_LOAD_FACTOR) {
            if (deletedCount > table.length / 4) {
                resize(table.length);
            } else {
                resize(table.length * 2);
            }
        }

        int hash = hash(x);
        int firstDeleted = -1;

        for (int step = 0; step < table.length; step++) {
            int index = probing.probe(hash, step, table.length);

            if (states[index] == State.OCCUPIED && table[index] == x) {
                return;
            }

            if (states[index] == State.DELETED) {
                if (firstDeleted == -1) {
                    firstDeleted = index;
                }
                continue;
            }

            if (states[index] == State.EMPTY) {
                int target = (firstDeleted != -1) ? firstDeleted : index;
                place(target, x);
                return;
            }
        }

        if (firstDeleted != -1) {
            place(firstDeleted, x);
            return;
        }

        resize(table.length * 2);
        insert(x);
    }

    @Override
    public boolean search(int x) {
        return findIndex(x) != -1;
    }

    @Override
    public void remove(int x) {
        int index = findIndex(x);
        if (index == -1) {
            return;
        }

        states[index] = State.DELETED;
        size--;
        deletedCount++;
    }

    private int findIndex(int x) {
        int hash = hash(x);

        for (int step = 0; step < table.length; step++) {
            int index = probing.probe(hash, step, table.length);

            if (states[index] == State.EMPTY) {
                return -1;
            }

            if (states[index] == State.OCCUPIED && table[index] == x) {
                return index;
            }
        }

        return -1;
    }

    private void place(int index, int x) {
        if (states[index] == State.DELETED) {
            deletedCount--;
        }

        table[index] = x;
        states[index] = State.OCCUPIED;
        size++;
    }

    private void resize(int newCapacity) {
        int[] oldTable = table;
        State[] oldStates = states;

        table = new int[nextPowerOfTwo(Math.max(4, newCapacity))];
        states = new State[table.length];

        Arrays.fill(states, State.EMPTY);

        int oldSize = size;
        size = 0;
        deletedCount = 0;

        for (int i = 0; i < oldTable.length; i++) {
            if (oldStates[i] == State.OCCUPIED) {
                reinsert(oldTable[i]);
            }
        }

        size = oldSize;
    }

    private void reinsert(int x) {
        int hash = hash(x);

        for (int step = 0; step < table.length; step++) {
            int index = probing.probe(hash, step, table.length);
            if (states[index] == State.EMPTY) {
                table[index] = x;
                states[index] = State.OCCUPIED;
                return;
            }
        }

        throw new IllegalStateException("Rehash failed");
    }

    private int hash(int x) {
        return (x ^ (x >>> 16)) & (table.length - 1);
    }

    private static int nextPowerOfTwo(int value) {
        int capacity = 1;
        while (capacity < value) {
            capacity <<= 1;
        }
        return capacity;
    }
}