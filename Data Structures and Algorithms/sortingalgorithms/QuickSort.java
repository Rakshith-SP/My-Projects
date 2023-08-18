package projects.exercise.sortingalgorithms;

public class QuickSort {

    public void sort(int[] array) {
        sort(array, 0, array.length-1);
    }

    private void sort(int[] array, int start, int end) {
        if (start == end || end < start)
            return;

        int pivotIndex = partition(array, start, end);
        sort(array, start, pivotIndex-1);
        sort(array, pivotIndex+1, end);
    }

    private int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int pivotIndex = end;
        int boundary = start - 1;
        for (int i = start; i < end; i++) {
            if (array[i] < pivot)
                swapItems(array, i, ++boundary);
        }
        swapItems(array, ++boundary, end);
        if (boundary >= start) {
            if (array[boundary] == pivot)
                pivotIndex = boundary;
        }

        return pivotIndex;
    }

    private void swapItems(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
