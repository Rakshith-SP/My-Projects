package projects.exercise.datastructures.heap;

import java.util.Arrays;

public class HeapifyMain {
    private static int moves = 0;

    public static void main(String[] args) {
        // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        // 5, 3, 8, 4, 1, 2
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        heapify(numbers);
        System.out.println(Arrays.toString(numbers));
        System.out.println(moves);
    }

    private static void heapify(int[] numbers) {
        // For MaxHeap, the reverse iteration prove to have fewer number of swaps/moves

        for (int i = numbers.length - 1; i >= 0; i--) {
            bubbleDown(i, numbers);
            bubbleUp(i, numbers);
            // heapify(i, numbers); // mosh solution
        }
    }

    private static void swap(int[] numbers, int i, int targetIndex) {
        int tempValue = numbers[i];
        numbers[i] = numbers[targetIndex];
        numbers[targetIndex] = tempValue;
    }

    private static boolean toBubbleDown(int index, int[] numbers) {
        if (getRightChildIndex(index) < numbers.length)
            return (numbers[index] < numbers[getLeftChildIndex(index)] || numbers[index] < numbers[getRightChildIndex(index)]);

        if (getLeftChildIndex(index) < numbers.length)
            return numbers[index] < numbers[getLeftChildIndex(index)];

        return false;
    }

    private static void bubbleDown(int index, int[] numbers) {
        while (toBubbleDown(index, numbers)) {
            moves++;
            int targetIndex = getTargetIndex(index, numbers);
            swap(numbers, index, targetIndex);
            index = targetIndex;
        }
    }

    private static void bubbleUp(int index, int[] numbers) {
        while (toBubbleUp(index, numbers)) {
            moves++;
            swap(numbers, index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private static boolean toBubbleUp(int index, int[] numbers) {
        return index > 0 && numbers[getParentIndex(index)] < numbers[index];
    }

    private static int getTargetIndex(int index, int[] numbers) {
        if (getRightChildIndex(index) < numbers.length)
            return numbers[getLeftChildIndex(index)] > numbers[getRightChildIndex(index)] ? getLeftChildIndex(index) : getRightChildIndex(index);

        else return getLeftChildIndex(index);
    }

    private static int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    private static int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    private static int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private static void heapify(int index, int[] array) {
        int largerValueIndex = index;

        int leftIndex = (index * 2) + 1;
        if (leftIndex < array.length && array[leftIndex] > array[largerValueIndex])
            largerValueIndex = leftIndex;

        int rightIndex = (index * 2) + 2;
        if (rightIndex < array.length && array[rightIndex] > array[largerValueIndex])
            largerValueIndex = rightIndex;

        if (index == largerValueIndex)
            return;
        swap(array, index, largerValueIndex);
        heapify(largerValueIndex, array);
    }

    public static int getKthLargestItem(int[] array, int k) {
        Heap heap = new Heap();

        if (k < 1 || k > array.length)
            throw new IllegalArgumentException();

        for (int number : array) {
            heap.insert(number);
        }

        for (int i = 0; i < k - 1; i++) {
            heap.remove();
        }

        return heap.max();
    }
}
