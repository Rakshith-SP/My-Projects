package projects.exercise.datastructures.queue;

import java.util.PriorityQueue;

public class PriorityQueueMain {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue= new PriorityQueue<>();
        projects.exercise.datastructures.queue.PriorityQueue myPriorityQueue = new projects.exercise.datastructures.queue.PriorityQueue(5);

        priorityQueue.add(4);
        priorityQueue.add(3);
        priorityQueue.add(5);
        priorityQueue.add(2);

        System.out.println("content : " + priorityQueue);
        System.out.println("extracting elements : ");
        while(!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.remove());
        }
        System.out.println("We can see the contents are not in the order as extracted\n");

        myPriorityQueue.enqueue(4);
        myPriorityQueue.enqueue(3);
        myPriorityQueue.enqueue(5);
        myPriorityQueue.enqueue(2);

        System.out.println(myPriorityQueue);
    }
}
