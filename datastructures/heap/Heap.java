package projects.exercise.datastructures.heap;

import java.util.Arrays;

public class Heap {
    private final int[] array = new int[10];
    private int pointer = 0;

    public void insert(int value) {
        if (isFull())
            throw new IllegalStateException();

        array[pointer++] = value;
        bubbleUp(pointer-1);
    }

    public int remove() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        int value = array[0];
        array[0] = array[--pointer];
        bubbleDown(0);
        return value;
    }

    public boolean isFull() {
        return pointer == array.length;
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    public int size() {
        return pointer;
    }

    public boolean isMaxHeap(int[] array) {
        return isMaxHeap(0, array);
    }

    private boolean isMaxHeap(int index, int[] array) {
        int lastParentIndex = (array.length - 2) / 2;
        if (index > lastParentIndex)
            return true;

        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;

        boolean isValidParent = array[index] >= array[leftChildIndex] &&
                        array[index] >= array[rightChildIndex];

        return isValidParent &&
                isMaxHeap(leftChildIndex, array) &&
                isMaxHeap(rightChildIndex, array);
    }

    public int max() {
        if (isEmpty())
            throw new IllegalStateException();

        return array[0];
    }
    private void bubbleUp(int index) {
        if (checkToBubbleUp(index)) {
            int parentIndex = parentIndexOf(index);
            swap(index, parentIndex);
            bubbleUp(parentIndex);
        }
    }
    private boolean checkToBubbleUp(int index) {
        return index > 0 && array[parentIndexOf(index)] < array[index];
    }
    private void bubbleDown(int index) {
        int leftChildIndex = (index * 2) + 1;
        int rightChildIndex = (index * 2) + 2;
        if (checkToBubbleDown(index, leftChildIndex, rightChildIndex)) {
            int targetIndex = array[leftChildIndex] > array[rightChildIndex] ? leftChildIndex : rightChildIndex;
            swap(index, targetIndex);
            bubbleDown(targetIndex);
        }
    }
    private boolean checkToBubbleDown(int index, int leftChildIndex, int rightChildIndex) {
        if (leftChildIndex < pointer)
            return array[index] < array[leftChildIndex] || array[index] < array[rightChildIndex];
        return false;
    }
    private void swap(int first, int second) {
        int tempValue = array[first];
        array[first] = array[second];
        array[second] = tempValue;
    }
    private int parentIndexOf(int index) {
        return (index - 1)/2;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, pointer));
    }
}
