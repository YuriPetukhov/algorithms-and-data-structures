package hw11_optimal_search_trees.dataset;

import hw11_optimal_search_trees.libs.searching.trees.optimal.WeightedKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatasetToWeightedKeysConverter {

    public static List<WeightedKey> toWeightedKeys(int[] data) {
        Map<Integer, Long> freq = new HashMap<>();

        for (int value : data) {
            freq.merge(value, 1L, Long::sum);
        }

        List<WeightedKey> result = new ArrayList<>();
        for (var entry : freq.entrySet()) {
            result.add(new WeightedKey(entry.getKey(), entry.getValue()));
        }

        return result;
    }
}
