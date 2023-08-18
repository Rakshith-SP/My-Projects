package projects.exercise.datastructures.heap;

public class PriorityQueueWithHeap {
    Heap heap = new Heap();

    public void enqueue(int value) {
        heap.insert(value);
    }

    public int dequeue(int value) {
        return heap.remove();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public boolean isFull() {
        return heap.isFull();
    }

    public int size() {
        return heap.size();
    }
}
