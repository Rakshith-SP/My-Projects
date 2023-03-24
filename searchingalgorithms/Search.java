package projects.exercise.searchingalgorithms;

public class Search {

    public int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == target)
                return i;

        return -1;
    }

    public int binarySearchRecursive(int[] array, int target) {
        return binarySearchRecursive(array, target, 0, array.length - 1);
    }

    private int binarySearchRecursive(int[] array, int target, int start, int end) {
        if (start > end)
            return -1;

        int middle = (start + end) / 2;

        if (array[middle] == target)
            return middle;

        if (array[middle] > target)
            return binarySearchRecursive(array, target, start, middle - 1);

        if (array[middle] < target)
            return binarySearchRecursive(array, target, middle + 1, end);

        return -1;
    }

    public int binarySearchIterative(int[] array, int target) {
        int start = 0;
        int end = array.length - 1;

        while (true) {
            if (start > end)
                return -1;

            int middle = (start + end) / 2;

            if (array[middle] == target)
                return middle;

            if (array[middle] > target) {
                end = middle - 1;
                continue;
            }

            if (array[middle] < target) {
                start = middle + 1;
                continue;
            }

            return -1;
        }
    }

    public int ternarySearch(int[] array, int target) {
        return ternarySearch(array, target, 0, array.length - 1);
    }

    private int ternarySearch(int[] array, int target, int start, int end) {
        if (start > end)
            return -1;

        int partitionSize = (end - start) / 3;
        int middle1 = start + partitionSize;
        int middle2 = end - partitionSize;

        if (array[middle1] == target)
            return middle1;
        if (array[middle2] == target)
            return middle2;

        if (target < array[middle1])
            return ternarySearch(array, target, start, middle1 - 1);
        else if (target > array[middle1] && target < array[middle2])
            return ternarySearch(array, target, middle1 + 1, middle2 - 1);
        else
            return ternarySearch(array, target, middle2 + 1, end);
    }

    public int jumpSearch(int[] array, int target) {
        int blockSize = (int) Math.sqrt(array.length);
        int start = 0;
        int next = blockSize;

        while (start < array.length && target > array[next - 1]) {
            start = next;
            next += blockSize;
            if (next > array.length)
                next = array.length;
        }

        for (int i = start; i < next; i++)
            if (target == array[i])
                return i;

        return -1;
    }

    public int exponentialSearch(int[] array, int target) {
        int bound = 1;

        while (bound < array.length && target > array[bound])
            bound *= 2;

        int start = bound / 2;
        int end = Math.min(bound, array.length - 1);
        return binarySearchRecursive(array, target, start, end);
    }
}
