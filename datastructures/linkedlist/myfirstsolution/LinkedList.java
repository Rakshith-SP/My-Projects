package projects.exercise.datastructures.linkedlist.myfirstsolution;

import java.util.NoSuchElementException;

public class LinkedList {
    private Node first;
    private Node last;
    private boolean isFirstSet = false;
    private boolean isLastSet = false;

    public void addFirst(int e) {
        if (isFirstSet && isLastSet) {
            Node node = new Node();
            node.setValue(e);
            node.setNext(first);
            first = node;
        }
        else if (!isLastSet) {
            this.last = new Node();
            last.setValue(e);
            isLastSet = true;
            if (isFirstSet) {
                Node temp = first;
                first = last;
                last = temp;
                first.setNext(last); //changed here
            }
        }
        else {
            this.first = new Node();
            first.setValue(e);
            isFirstSet = true;
            if (isLastSet)
                first.setNext(last);
        }
    }
    public void addLast(int e) {
        if (isLastSet && isFirstSet) {
            Node node = new Node();
            node.setValue(e);
            last.setNext(node);
            last = node;
        }
        else if (!isFirstSet) {
            this.first = new Node();
            first.setValue(e);
            isFirstSet = true;
            if (isLastSet) {
                Node temp = first;
                first = last;
                last = temp;
                first.setNext(last); // second swap
            }
        }
        else {
            this.last = new Node();
            last.setValue(e);
            isLastSet = true;
            if (isFirstSet)
                first.setNext(last);
        }
    }

    public void deleteFirst() {
        if (first == null && last == null)
            throw new NoSuchElementException();
        else if (first == null)
            last = null;
        else if (last == null)
            first = null;
        else if (first.getNext() == last)
            first = null;
        else {
            first = first.getNext();
        }
    }

    public void deleteLast() {
        if (first == null && last == null)
            throw new NoSuchElementException();
        else if (first == null)
            last = null;
        else if (last == null)
            first = null;
        else if (first.getNext() == last)
            last = null;
        else {
            Node tempNode = first;
            while (tempNode.getNext() != last) {
                tempNode = tempNode.getNext();
            }
            last = tempNode;
        }
    }

    public boolean contains(int e) {
        if (first == null && last == null)
            throw new NoSuchElementException();
        else if (first == null) {
            return last.getValue() == e;
        }
        else if (last == null) {
            return first.getValue() == e;
        }
        else {
            Node tempNode = first;
            while (tempNode != last) {
                if (tempNode.getValue() == e)
                    return true;
                tempNode = tempNode.getNext();
            }
        }
        return last.getValue() == e;
    }

    public int indexOf(int e) {
        int index = 0;
        if (first == null && last == null)
            throw new NoSuchElementException();
        else if (first == null) {
            if (last.getValue() == e)
                return 0;
        }
        else if (last == null) {
            if (first.getValue() == e)
                return 0;
        }
        else {
            Node tempNode = first;
            while (tempNode != last) {
                if (tempNode.getValue() == e)
                    return index;
                tempNode = tempNode.getNext();
                index++;
            }
        }
        if (last.getValue() == e) {
            return index;
        }
        return -1;
    }

    @Override
    public String toString() {
        if (first == null && last == null) {
            return "[]";
        }
        else if (first == null) {
            return "[" + last.getValue() + "]";
        }
        else if (last == null) {
            return "[" + first.getValue() + "]";
        }
        else {
            StringBuilder stringBuilder = new StringBuilder("[");
            Node temp = first;
            do {
                stringBuilder.append(temp.getValue());
                stringBuilder.append(", ");
                temp = temp.getNext();
            }while (temp != last);

            stringBuilder.append(last.getValue());
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }
}
