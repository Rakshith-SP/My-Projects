package projects.exercise.datastructures.queue;

import java.util.Arrays;

public class PriorityQueue {
    int[] array;
    int frontPointer = 0;
    int rearPointer = 0;
    int count = 0;

    public PriorityQueue(int capacity) {
        array = new int[capacity];
    }

    public void enqueue(int element) {
        if (isFull())
            resize();


        if (isEmpty()){
            array[rearPointer] = element;
            rearPointer = (rearPointer + 1) % array.length;
            count++;
            return;
        }

        int pointer = rearPointer - 1;

        while (true) {
            if (pointer == frontPointer) {
                if (element >= array[pointer]) {
                    array[(pointer+1) % array.length] = element;
                }
                else {
                    array[(pointer+1) % array.length] = array[pointer];
                    array[pointer] = element;
                }
                rearPointer = (rearPointer + 1) % array.length;
                count++;
                return;
            }

            if (element >= array[pointer]) {
                if (isEmpty()) {
                    array[(pointer+1) % array.length] = array[pointer];
                    array[pointer] = element;
                }
                else {
                    array[(pointer+1) % array.length] = element;
                    rearPointer = (rearPointer + 1) % array.length;
                }
                count++;
                return;
            }
            else {
                array[(pointer+1) % array.length] = array[pointer];
                pointer--;
            }
        }

    }

    public int dequeue() {
        if (isEmpty())
            throw new IllegalStateException();

        var item = array[frontPointer];
        frontPointer = (frontPointer + 1) % array.length;
        count--;
        return item;
    }

    public int peek() {
        return array[frontPointer];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == array.length;
    }

    public int[] toArray() {
        if (rearPointer <= frontPointer && count != 0) {
            int[] array1 = Arrays.copyOfRange(array, frontPointer, array.length);
            int[] array2 = Arrays.copyOfRange(array, 0, rearPointer);

            int[] result = new int[array1.length+ array2.length];
            System.arraycopy(array1, 0, result, 0, array1.length);
            System.arraycopy(array2, 0, result, array1.length, array2.length);

            return result;
        }
        return Arrays.copyOfRange(array, frontPointer, rearPointer);
    }

    public int size() {
        return count;
    }

    private void resize() {
        int[] resizedArray = toArray();
        array = new int[array.length * 2];
        System.arraycopy(resizedArray, 0, array, 0, resizedArray.length);
        frontPointer = 0;
        rearPointer = size();
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
