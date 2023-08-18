package projects.exercise.sortingalgorithms;

import java.util.Arrays;

public class MergeSort {

    public void sort(int[] array) {
        int midIndex = array.length / 2;

        if (midIndex == 0)
            return;

        if (array.length == 2) {
            if (array[0] > array[1]) {
                int temp = array[0];
                array[0] = array[1];
                array[1] = temp;
            }
            return;
        }

        int[] leftSubArray = Arrays.copyOfRange(array, 0, midIndex);
        int[] rightSubArray = Arrays.copyOfRange(array, midIndex, array.length);

        sort(leftSubArray);
        sort(rightSubArray);

        merge(leftSubArray, rightSubArray, array);
    }

    private void merge(int[] leftSubArray, int[] rightSubArray, int[] array) {
        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;

        while (leftIndex < leftSubArray.length || rightIndex < rightSubArray.length) {
            if (leftSubArray[leftIndex] > rightSubArray[rightIndex])
                array[index++] = rightSubArray[rightIndex++];
            else
                array[index++] = leftSubArray[leftIndex++];

            if (leftIndex == leftSubArray.length) {
                System.arraycopy(rightSubArray, rightIndex, array, index,rightSubArray.length - rightIndex);
                break;
            }
            else if (rightIndex == rightSubArray.length) {
                System.arraycopy(leftSubArray, leftIndex, array, index,leftSubArray.length - leftIndex);
                break;
            }
        }
    }
}
