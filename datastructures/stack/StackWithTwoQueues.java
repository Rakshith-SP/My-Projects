package projects.exercise.datastructures.stack;

import java.util.ArrayDeque;
import java.util.Queue;

public class StackWithTwoQueues {
    Queue<Integer> queue1 = new ArrayDeque<>(); //always for push
    Queue<Integer> queue2 = new ArrayDeque<>(); //always for pop
    int topOfStack = 0;

    //O(1)
    public void push(int element) {
        queue1.add(element);
        topOfStack = element;
    }

    //O(n)
    public int pop() {
        if (isEmpty())
            throw new IllegalStateException();

        while (queue1.size() > 1) {
            topOfStack = queue1.remove();
            queue2.add(topOfStack);
        }
        swapQueues();
        return queue2.remove();
    }

    //O(n)
    public int peek() {
        if (isEmpty())
            throw new IllegalStateException();

        return topOfStack;
    }

    //O(1)
    public int size() {
        if (isEmpty())
            return 0;

        return queue1.size();
    }

    //O(1)
    public boolean isEmpty(){
        return (queue1.isEmpty());
    }

    @Override
    public String toString() {
        return queue1.toString();
    }

    private void swapQueues() {
        var temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

}
