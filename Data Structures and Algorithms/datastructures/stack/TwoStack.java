package projects.exercise.datastructures.stack;

import java.util.Arrays;

public class TwoStack {
    private final int[] array1 = new int[5];
    private final int[] array2 = new int[5];
    private int pointer1 = 0;
    private int pointer2 = 0;

    public void push1(int element) {
        if (pointer1 == array1.length)
            throw new StackOverflowError();

        array1[pointer1++] = element;
    }

    public void push2(int element) {
        if (pointer2 == array2.length)
            throw new StackOverflowError();

        array2[pointer2++] = element;
    }

    public int pop1() {
        if (pointer1 == 0)
            throw new IllegalStateException();

        return array1[--pointer1];
    }

    public int pop2() {
        if (pointer2 == 0)
            throw new IllegalStateException();

        return array2[--pointer2];
    }

    public int peek1() {
        if (pointer1 == 0)
            throw new IllegalStateException();

        return array1[pointer1 -1];
    }

    public int peek2() {
        if (pointer2 == 0)
            throw new IllegalStateException();

        return array2[pointer2 -1];
    }

    public boolean isEmpty1() {
        return pointer1 == 0;
    }

    public boolean isEmpty2() {
        return pointer2 == 0;
    }

    public boolean isFull1() {
        return pointer1 == array1.length;
    }

    public boolean isFull2() {
        return pointer2 == array2.length;
    }

    public int size1() {
        return pointer1;
    }

    public int size2() {
        return pointer2;
    }

    public int[] toArray1() {
        return Arrays.copyOfRange(array1, 0, pointer1);
    }

    public int[] toArray2() {
        return Arrays.copyOfRange(array2, 0, pointer2);
    }

    @Override
    public String toString() {

        return "stack1 : " + Arrays.toString(toArray1()) + "\n" + "stack2 : " + Arrays.toString(toArray2());
    }
}
