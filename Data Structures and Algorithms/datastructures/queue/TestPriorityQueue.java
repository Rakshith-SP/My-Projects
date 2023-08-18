package projects.exercise.datastructures.queue;

import org.junit.jupiter.api.Test;

public class TestPriorityQueue {
    PriorityQueue queue = new PriorityQueue(5);
    String expectedResult = "[1, 2, 3, 4]";

    @Test
    public void one() {
        //1234
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void two() {
        //1243
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(3);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void three() {
        //1324
        queue.enqueue(1);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(4);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void four() {
        //1342
        queue.enqueue(1);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(2);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void five() {
        //1423
        queue.enqueue(1);
        queue.enqueue(4);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void six() {
        //1432
        queue.enqueue(1);
        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(2);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void seven() {
        //2134
        queue.enqueue(2);
        queue.enqueue(1);
        queue.enqueue(3);
        queue.enqueue(4);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void eight() {
        //2143
        queue.enqueue(2);
        queue.enqueue(1);
        queue.enqueue(4);
        queue.enqueue(3);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void nine() {
        //2314
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(4);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void ten() {
        //2341
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(1);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void eleven() {
        //2413
        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(1);
        queue.enqueue(3);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void twelve() {
        //2431
        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(1);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void thirteen() {
        //3124
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(4);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void fourteen() {
        //3142
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(4);
        queue.enqueue(2);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void fifteen() {
        //3214
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(1);
        queue.enqueue(4);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void sixteen() {
        //3241
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(1);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void seventeen() {
        //3412
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(1);
        queue.enqueue(2);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void eighteen() {
        //3421
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(2);
        queue.enqueue(1);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void nineteen() {
        //4123
        queue.enqueue(4);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void twenty() {
        //4132
        queue.enqueue(4);
        queue.enqueue(1);
        queue.enqueue(3);
        queue.enqueue(2);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void twentyOne() {
        //4213
        queue.enqueue(4);
        queue.enqueue(2);
        queue.enqueue(1);
        queue.enqueue(3);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void twentyTwo() {
        //4231
        queue.enqueue(4);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void twentyThree() {
        //4312
        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(2);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void twentyFour() {
        //4321
        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(1);

        System.out.println(queue.toString().equals(expectedResult));
    }

    @Test
    public void oneElement() {
        queue.enqueue(4);

        System.out.println(queue.toString().equals("[4]"));
    }

    @Test
    public void twoElement() {
        queue.enqueue(4);
        queue.enqueue(1);

        System.out.println(queue.toString().equals("[1, 4]"));
    }

    @Test
    public void threeElement() {
        queue.enqueue(4);
        queue.enqueue(2);
        queue.enqueue(1);

        System.out.println(queue.toString().equals("[1, 2, 4]"));
    }

    @Test
    public void empty() {
        try {
            queue.dequeue();
        }
        catch (IllegalStateException illegalStateException) {
            System.out.println(illegalStateException + " occurred");
        }
    }

    @Test
    public void full() {
        try {
            queue.enqueue(60);
            queue.enqueue(40);
            queue.enqueue(50);
            queue.enqueue(30);
            queue.enqueue(10);
            queue.enqueue(20);
        }
        catch (IllegalStateException illegalStateException) {
            System.out.println(illegalStateException + " occurred");
        }
        System.out.println(queue.size());
        System.out.println(queue);
    }

}
