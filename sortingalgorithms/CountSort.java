package projects.exercise.sortingalgorithms;

public class CountSort {
    public void sort(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int number : array)
            if (number > max)
                max = number;

        sort(array, max);
    }
    public void sort(int[] array, int max) {
        int[] baseArray = new int[max + 1];
        for (int item : array) {
            if (item < 0)
                throw new IllegalArgumentException("Negative numbers are not allowed in this sorting algorithm");
            if (item > max)
                throw new IllegalArgumentException("This algorithm sorts only for the numbers less than " + max);
            baseArray[item]++;
        }

        int index = 0;
        for (int i = 0; i < baseArray.length; i++)
            if (baseArray[i] > 0)
                for (int j = 0; j < baseArray[i]; j++)
                    array[index++] = i;
    }
}
