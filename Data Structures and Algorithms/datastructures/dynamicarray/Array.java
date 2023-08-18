package projects.exercise.datastructures.dynamicarray;

public class Array {
    private int[] array;

    private int lastItemIndex = -1;

    public Array(int length) {
        array = new int[length];
    }

    public void insert(int item) {
        lastItemIndex++;
        try {
            array[lastItemIndex] = item;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            extend();
            array[lastItemIndex] = item;
        }
        catch (Exception e) {
            System.out.println("Something went wrong : " + e);
        }
    }

    public void removeAt(int index) {
        if (index > lastItemIndex || index < 0) {
            throw new RuntimeException("Not possible");

        int j=0;
        int[] tempArray = new int[array.length-1];
        for (int i = 0; i < lastItemIndex+1; i++) {
            if (i != index) {
                tempArray[j] = array[i];
                j++;
            }
        }
        array = new int[tempArray.length];
        System.arraycopy(tempArray, 0, array, 0, tempArray.length);
    }

    public void print() {
        for (int i = 0; i < lastItemIndex+1; i++) {
            System.out.println(array[i]);
        }
    }

    public int indexOf(int item) {
        for (int i = 0; i < lastItemIndex+1; i++) {
            if (array[i] == item)
                return i;
        }
        return -1;
    }

    public int max() {
        int currentMax = array[0];
        for (int num: array) {
            if (num > currentMax) {
                currentMax = num;
            }
        }
        return currentMax;
    }

    public Array intersect(Array anotherArray) {
        Array commonItems = new Array(array.length);

        for (int i = 0; i <= lastItemIndex; i++) {
            for (int j = 0; j <= anotherArray.size(); j++) {
                if (array[i] == anotherArray.array[j])
                    commonItems.insert(array[i]);
            }
        }
        return commonItems;
    }

    public void reverse() {
        int leftPointer = 0;
        int rightPointer = lastItemIndex;
        int tempNum;

        while (leftPointer < rightPointer) {
            tempNum = array[leftPointer];
            array[leftPointer] = array[rightPointer];
            array[rightPointer] = tempNum;
            leftPointer++;
            rightPointer--;
        }
    }

    public void insertAt(int item, int index) {
        if (index > lastItemIndex || index < 0) //it should be - index > length (user input)
            throw new IllegalArgumentException();

        if (lastItemIndex == array.length)
            extend();

        for (int i = lastItemIndex; i >= index ; i--)
            array[i+1] = array[i];

        array[index] = item;
        lastItemIndex++;
    }
    private void extend() {
        //no need to create two separate arrays. Just refer the created new array
        int[] temp_array = new int[array.length];
        System.arraycopy(array, 0, temp_array, 0, array.length);
        array = new int[(int) Math.ceil(temp_array.length * 1.25)];
        System.arraycopy(temp_array, 0, array, 0, temp_array.length);
    }

    public int size() {
        return lastItemIndex;
    }
}
