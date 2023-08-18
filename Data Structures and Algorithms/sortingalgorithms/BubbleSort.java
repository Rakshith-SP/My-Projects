package projects.exercise.sortingalgorithms;

public class BubbleSort {

    public void sort(int[] array) {
        if (array.length == 0 || array.length == 1)
            return;

        boolean isSorted;
        for (int i = 0; i < array.length; i++) {
            isSorted = true;
            for (int j = 1; j < array.length - i; j++) {
                if (array[j] < array[j - 1]) {
                    swapItems(array, j, j - 1);
                    isSorted = false;
                }
            }
            if (isSorted)
                return;
        }
    }

    private void swapItems(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
