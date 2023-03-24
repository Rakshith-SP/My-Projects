package projects.exercise.sortingalgorithms;

import java.util.Arrays;

public class Report {
    public static void main(String[] args) {
        int[] array = {1000, 5000, 100000, 500000, 1000000, 5000000, 100000000, 1000_000_000};
        System.out.println(Arrays.toString(array));
        //CountSort sorter = new CountSort();
        CountSortPlus sorter = new CountSortPlus();

        long summation = 0;
        int repetition = 1;

        for (int i = 0; i < repetition; i++) {
            long start = System.currentTimeMillis();
            sorter.sort(array);
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            summation += timeElapsed;
        }
        long average = summation / repetition;

        System.out.println(Arrays.toString(array));
        System.out.println("Time of execution : " + average + "ms");
    }
}
