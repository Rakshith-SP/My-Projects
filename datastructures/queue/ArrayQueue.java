package projects.exercise.datastructures.queue;

import java.util.Arrays;

public class ArrayQueue {
    int[] array;
    int frontPointer = 0;
    int rearPointer = 0;
    int count = 0;

    public ArrayQueue(int capacity) {
        array = new int[capacity];
    }

    public void enqueue(int element) {
        if (isFull())
            throw new IllegalStateException();

        array[rearPointer] = element;
        rearPointer = (rearPointer + 1) % array.length;
        count++;
    }

    public int dequeue() {
        if (isEmpty())
            throw new IllegalStateException();

        var item = array[frontPointer];
        frontPointer = (frontPointer + 1) % array.length;
        count--;
        return item;
    }

    public int peek() {
        return array[frontPointer];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == array.length;
    }

    public int[] toArray() {
        int pointer = frontPointer;
        int[] result = new int[count];

        for (int i = 0; i < count; i++) {
            result[i] = array[pointer];
            pointer = (pointer + 1) % array.length;
        }

        return result;

        /* my previous solution
        if (rearPointer <= frontPointer && count != 0) {
            int[] array1 = Arrays.copyOfRange(array, frontPointer, array.length);
            int[] array2 = Arrays.copyOfRange(array, 0, rearPointer);

            int[] result = new int[array1.length+ array2.length];
            System.arraycopy(array1, 0, result, 0, array1.length);
            System.arraycopy(array2, 0, result, array1.length, array2.length);

            return result;
        }
        return Arrays.copyOfRange(array, frontPointer, rearPointer);*/
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
