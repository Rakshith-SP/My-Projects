package projects.exercise.sortingalgorithms;

public class InsertionSort {

    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int currInt = array[i];
            int j = i-1;
            while(j >= 0 && array[j] > currInt) {
                array[j+1] = array[j];
                j--;
            }
            array[j + 1] = currInt;
        }
    }
}
