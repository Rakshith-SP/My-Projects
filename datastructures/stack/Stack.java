package projects.exercise.datastructures.stack;

import java.util.Arrays;

public class Stack {
    private final int[] array = new int[5];
    private int pointer = 0;

    public void push(int element) {
        if (pointer == array.length)
            throw new StackOverflowError();

        array[pointer++] = element;
    }

    public int pop() {
        if (pointer == 0)
            throw new IllegalStateException();

        return array[--pointer];
    }

    public int peek() {
        if (pointer == 0)
            throw new IllegalStateException();

        return array[pointer-1];
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    public int size() {
        return pointer;
    }

    public int[] toArray() {
        return Arrays.copyOfRange(array, 0, pointer);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
