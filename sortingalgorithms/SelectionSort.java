package projects.exercise.sortingalgorithms;

import java.util.Arrays;

public class SelectionSort {

    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int index = indexOfSmallest(array, i);
            if (index != i)
                swap(array, index, i);
            System.out.println(Arrays.toString(array));
        }
    }

    private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private int indexOfSmallest(int[] array, int startIndex) {
        int minIndex = startIndex;
        for (int i = startIndex; i < array.length; i++) {
            if (array[i] < array[minIndex])
                minIndex = i;
        }
        return minIndex;
    }

}
