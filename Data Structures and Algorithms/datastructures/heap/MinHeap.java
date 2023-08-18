package projects.exercise.datastructures.heap;

public class MinHeap {
    public static class Node {

        private final int key;
        private final String value;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value='" + value + '\'' +
                    '}';
        }

    }
    private final Node[] items = new Node[10];
    private int pointer = 0;

    public void insert(int key, String value) {
        if (isFull())
            throw new IllegalStateException();

        items[pointer++] = new Node(key, value);
        bubbleUp();
    }

    public String remove() {
        if (isEmpty())
            throw new IllegalStateException();

        Node returnItem = items[0];
        items[0] = items[--pointer];
        bubbleDown();
        return returnItem.value;
    }

    public boolean isFull() {
        return pointer == items.length;
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    private void bubbleUp() {
        int index = pointer - 1;
        while(index > 0 && items[index].key < items[parentIndex(index)].key) {
            swapNodes(index, parentIndex(index));
            index = parentIndex(index);
        }
    }
    private void bubbleDown() {
        int index = 0;
        while(index <= pointer && !isValidParent(index)) {
            int targetIndex = getTargetIndex(index);
            swapNodes(index, targetIndex);
            index = targetIndex;
        }
    }
    private int getTargetIndex(int index) {
        if (!hasLeftChild(index))
            return index;

        if (!hasRightChild(index))
            return leftIndex(index);

        return (leftChildOf(index) < rightChildOf(index)) ?
                leftIndex(index) : rightIndex(index);

    }
    private boolean hasLeftChild(int index) {
        return leftIndex(index) <= pointer;
    }
    private boolean hasRightChild(int index) {
        return rightIndex(index) <= pointer;
    }
    private boolean isValidParent(int index) {
        if (!hasLeftChild(index))
            return true;

        boolean isValid = items[index].key <= leftChildOf(index);

        if (hasRightChild(index))
            isValid &= items[index].key <= rightChildOf(index);

        return isValid;
    }
    private int leftChildOf(int index) {
        return items[leftIndex(index)].key;
    }
    private int rightChildOf(int index) {
        return items[rightIndex(index)].key;
    }
    private void swapNodes(int first, int second) {
        Node tempValue = items[first];
        items[first]= items[second];
        items[second] = tempValue;
    }
    private int parentIndex(int index) {
        return (index - 1) / 2;
    }
    private int leftIndex(int index) {
        return (index * 2) + 1;
    }
    private int rightIndex(int index) {
        return (index * 2) + 2;
    }
}
