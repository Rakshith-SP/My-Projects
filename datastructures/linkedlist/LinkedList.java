package projects.exercise.datastructures.linkedlist;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class LinkedList {

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

    public void addFirst(int item) {
        Node node = new Node(item);

        if (isEmpty(first))
            last = node;
        else
            node.next = first;
        first = node;

        length++;
    }

    public void addLast(int item) {
        Node node = new Node(item);

        if (isEmpty(last))
            first = node;
        else
            last.next = node;
        last = node;

        length++;
    }

    public void removeFirst() {
        if (isEmpty(first))
            throw new NoSuchElementException();

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
    }

    public void removeLast() {
        if (isEmpty(last))
            throw new NoSuchElementException();

        if (last == first) {
            last = null;
            first = null;
        }
        else {
            Node previous = getPreviousOf(last);
            previous.next = null;
            last = previous;
        }

        length--;
    }
    public int indexOf(int item) {
        if (isEmpty(first))
            throw new NoSuchElementException();

        int index = 0;
        Node tempNode = first;
        while (tempNode != null) {
            if (item == tempNode.value)
                return index;
            tempNode = tempNode.next;
            index++;
        }
        return -1;
    }

    public boolean contains(int item) {
        return indexOf(item) != -1;
    }

    public int size() {
        return length;
    }

    public void reverse() {
        if (length == 0 || length == 1) return;

        Node prevNode = first;
        Node currNode = first.next;
        prevNode.next = null;
        while(currNode.next != null) {
            Node nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        currNode.next = prevNode;
        last = first;
        first = currNode;
    }

    public int getKthFromTheEnd(int k) {
        if (k <= 0)
            throw new NoSuchElementException();

        if (isEmpty(first))
            throw new IllegalStateException();

        if (k > length)
            throw new IllegalArgumentException();

        Node firstPointer = first;
        Node secondPointer = first;
        int distance = k-1;

        while (distance-- > 0)
            secondPointer = secondPointer.next;

        while (secondPointer != last) {
            secondPointer = secondPointer.next;
            firstPointer = firstPointer.next;
        }

        return firstPointer.value;
    }

    public int getFirst() {
        return first.value;
    }

    public void printMiddle() {
        if (isEmpty(first))
            throw new IllegalStateException();

        if (first == last || first.next == last) {
            System.out.println("[]");
            return;
        }

        Node fastPointer = first;
        Node slowPointer = first;
        while (fastPointer.next != last && fastPointer != last) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }

        if (fastPointer.next == last)
            System.out.println("[" + slowPointer.value + ", " + slowPointer.next.value + "]");
        else
            System.out.println(slowPointer.value);
    }
    public int[] toArray() {
        int[] array = new int[length];
        Node tempNode = first;
        int i=0;
        while (tempNode != null) {
            array[i] = tempNode.value;
            i++;
            tempNode = tempNode.next;
        }
        return array;
    }

    @Override
    public String toString() {
        if (first == null)
            throw new NoSuchElementException();

        return Arrays.toString(toArray());
    }

    private boolean isEmpty(Node node) {
        return node == null;
    }

    private Node getPreviousOf(Node node) {
        Node tempNode = first;
        while (tempNode.next != node)
            tempNode = tempNode.next;
        return tempNode;
    }

    /*Node newFirst = last;
    while (first != last) {
        Node node = last;
        last = getPreviousOf(last);
        node.next = last;
    }
    first = newFirst;
    last.next = null;*/

    /*        Node newLast = first;
        Node previousToFirst = null;

        while (first != last) {
            Node nextNode = first.next;
            first.next = null;

            Node swapNode = first;
            first = nextNode;
            nextNode = swapNode;

            Node tempNode = first;
            first = first.next;
            tempNode.next = nextNode;

            if (previousToFirst != null) {
                nextNode.next = previousToFirst;
            }
            previousToFirst = tempNode;
        }
        first.next = previousToFirst;
        last = newLast;*/
}
