package projects.exercise.sortingalgorithms;
//There is no need of constraint in limiting the range of input and the inputs being negative
//The solution for limiting the range of input is to sort numbers within the ranges for 100s individually
//      --> first, the inputs from 0 to 1000 are sorted.
//      --> then, the inputs from 1000 to 2000 are sorted, and then goes on
//To achieve this, we need a hashmap to store the repetitions of each number.
//Whenever a number is placed in its right position, that number will be removed from the hashmap
//The sorting continues until the hashmap becomes empty
//To skip some iterations we can keep track of numbers being in which range. Is it 0-1000? or 1000-2000?
//      --> this can be done by using another hashmap to keep track of ranges where the inputs lie.
//      --> this hashmap stores the ranges with the values as the no of inputs lie in that range.
//      --> this hashmap is used when setting new ranges to for loop.
//The constraint for negative numbers is yet to be solved

// sort the ranges and then feed it into the for loop to enhance the performance
// use the recursion to sort it

import java.util.HashMap;
import java.util.Map;

public class CountSortPlus {

    public void sort(int[] array) {
        Map<Integer, Integer> items = new HashMap<>();
        Map<Integer, Integer> ranges = new HashMap<>();
        int chunkSize = 1000;

        for (int number : array) {
            if (items.containsKey(number)) {
                int repetition = items.get(number);
                items.put(number, ++repetition);
            }
            else
                items.put(number, 1);

            int range = (number/chunkSize) + 1;
            if (ranges.containsKey(range)) {
                int repetition = ranges.get(range);
                ranges.put(range, ++repetition);
            }
            else
                ranges.put(range, 1);
        }

        // sort the ranges and then feed it into the for loop to enhance the performance
        // use the recursion to sort it

        int index = 0;
        int range = 1;
        while (!items.isEmpty()) {
            if (ranges.containsKey(range)) {
                int actualRange = range * chunkSize;
                int recordedElementCountInRange = ranges.get(range);
                int elementCount = 0;

                for (int i = 0; i <= actualRange; i++) {
                    if (items.containsKey(i)) {
                        for (int j = 0; j < items.get(i); j++) {
                            array[index++] = i;
                            elementCount++;
                        }
                    }
                    items.remove(i);
                    if (recordedElementCountInRange == elementCount)
                        break;
                }
            }

            ranges.remove(range);
            range++;
        }
    }


}
