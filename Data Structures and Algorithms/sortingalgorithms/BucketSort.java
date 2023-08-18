package projects.exercise.sortingalgorithms;

import java.util.*;

public class BucketSort {

    public void sort(int[] array, int numberOfBuckets) {
        List<List<Integer>> buckets = createBuckets(array, numberOfBuckets);

        int index = 0;
        for (List<Integer> bucket : buckets) {
            if (bucket != null) {
                Collections.sort(bucket);

                for (int number : bucket)
                    array[index++] = number;
            }
        }
    }

    private List<List<Integer>> createBuckets(int[] array, int numberOfBuckets) {
        List<List<Integer>> buckets = new ArrayList<>(numberOfBuckets);
        for (int i = 0; i < numberOfBuckets; i++)
            buckets.add(i, new ArrayList<>());

        for (int number : array) {
            int bucket = number / numberOfBuckets;
            buckets.get(bucket).add(number);
        }
        return buckets;
    }
}
