package projects.exercise.datastructures.queue;

import projects.exercise.datastructures.stack.Stack;

import java.util.Arrays;

public class StackQueue {
    Stack enqueueStack = new Stack();
    Stack dequeueStack = new Stack();

    public void enqueue(int element) {
        enqueueStack.push(element);
    }

    public int dequeue() {
        if (isEmpty())
            throw new IllegalStateException();

        extractEnqueueStack();
        return dequeueStack.pop();
    }

    public void peek() {
        if (isEmpty())
            throw new IllegalStateException();

        extractEnqueueStack();
        dequeueStack.peek();
    }

    public boolean isEmpty() {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }

    public int[] toArray() {
        int[] result = new int[enqueueStack.size() + dequeueStack.size()];
        int[] arrayFromDequeueStack = new int[dequeueStack.size()];
        int[] arrayFromEnqueueStack = new int[enqueueStack.size()];

        if (!dequeueStack.isEmpty()) {
            int[] tempArray = new int[arrayFromDequeueStack.length];
            arrayFromDequeueStack = dequeueStack.toArray();
            int index = 0;
            for (int i = arrayFromDequeueStack.length - 1; i >= 0 ; i--) {
                tempArray[index++] = arrayFromDequeueStack[i];
            }
            arrayFromDequeueStack = tempArray;
        }

        if (!enqueueStack.isEmpty())
            arrayFromEnqueueStack = enqueueStack.toArray();

        System.arraycopy(arrayFromDequeueStack, 0, result, 0, arrayFromDequeueStack.length);
        System.arraycopy(arrayFromEnqueueStack, 0, result, arrayFromDequeueStack.length, arrayFromEnqueueStack.length);

        return result;
    }

    private void extractEnqueueStack() {
        if (dequeueStack.isEmpty()) {
            while (!enqueueStack.isEmpty())
                dequeueStack.push(enqueueStack.pop());
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
