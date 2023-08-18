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
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
