package projects.exercise.datastructures.stack;

import java.util.Arrays;

public class MinStack {
    private final int[] array = new int[6];
    private final int[] minimums = new int[array.length];
    private final int[] minimumsIndexes = new int[array.length];
    private int pointer = 0;
    private int minimumsPointer = 0;
    public void push(int element) {
        if (pointer == array.length)
            throw new StackOverflowError();

        array[pointer++] = element;
        if (isMinimumsEmpty()){
            pushMinimum(element);
        }
        if (element < peekMinimum())
            pushMinimum(element);
    }
    public int pop() {
        if (pointer == 0)
            throw new IllegalStateException();

        if (pointer == minimumsIndexes[minimumsPointer-1])
            popMinimum();
        return array[--pointer];
    }
    public int peek() {
        if (pointer == 0)
            throw new IllegalStateException();

        return array[pointer -1];
    }
    public boolean isEmpty() {
        return pointer == 0;
    }

    private void pushMinimum(int element) {
        if (minimumsPointer == minimums.length)
            throw new StackOverflowError();

        minimums[minimumsPointer] = element;
        minimumsIndexes[minimumsPointer] = pointer;
        minimumsPointer++;
    }
    private void popMinimum() {
        if (minimumsPointer == 0)
            throw new IllegalStateException();

        --minimumsPointer;
    }
    private int peekMinimum() {
        if (minimumsPointer == 0)
            throw new IllegalStateException();

        return minimums[minimumsPointer -1];
    }
    private boolean isMinimumsEmpty() {
        return minimumsPointer == 0;
    }
    public int min() {
        return peekMinimum();
    }

    public int[] toArray() {
        return Arrays.copyOfRange(array, 0, pointer);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
