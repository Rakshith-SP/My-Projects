package projects.exercise.datastructures.queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class LinkedListQueue {
    private static class Node {
        private final int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node first;
    private Node last;
    private int length;

    //O(1)
    public void enqueue(int element) {

        Node node = new Node(element);

        if (isEmpty())
            first = node;
        else
            last.next = node;
        last = node;

        length++;
    }

    //O(1)
    public int dequeue() {

        if (isEmpty())
            throw new NoSuchElementException();

        int value = first.value;
        if (first == last) {
            first = null;
            last = null;
        }
        else {
            Node nextNode = first.next;
            first.next = null;
            first = nextNode;
        }
        length--;
        return value;
    }

    //O(1)
    public int peek() {
        if (isEmpty())
            throw new NoSuchElementException();

        return first.value;
    }

    //O(1)
    public int size() {
        return length;
    }

    //O(1)
    public boolean isEmpty() {
        return length == 0;
    }

    //O(n)
    public int[] toArray() {
        if (isEmpty())
            throw new IllegalStateException();

        int[] array = new int[length];
        Node tempNode = first;
        for (int i = 0; i < length; i++) {
            array[i] = tempNode.value;
            tempNode = tempNode.next;
        }
        return array;
    }

    //O(n)
    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
