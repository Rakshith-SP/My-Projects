// This is to test the addFirst and addLast methods. There are 12 scenarios for

package projects.exercise.datastructures.linkedlist;

import org.junit.jupiter.api.Test;

public class TestLinkedList {

    LinkedList myLinkedList = new LinkedList();
    java.util.LinkedList<Integer> linkedList = new java.util.LinkedList<>();

    @Test
    public void aaaa() {
        myLinkedList.addFirst(10);
        myLinkedList.addFirst(20);
        myLinkedList.addFirst(30);
        myLinkedList.addFirst(40);
        String myResult = myLinkedList.toString();

        linkedList.addFirst(10);
        linkedList.addFirst(20);
        linkedList.addFirst(30);
        linkedList.addFirst(40);
        String expectedResult = linkedList.toString();

        System.out.println(myResult.equals(expectedResult));
    }

//    @Test
//    public void baaa() {
//        myLinkedList.addLast(10);
//        myLinkedList.addFirst(20);
//        myLinkedList.addFirst(30);
//        myLinkedList.addFirst(40);
//        String myResult = myLinkedList.toString();
//
//        linkedList.addLast(10);
//        linkedList.addFirst(20);
//        linkedList.addFirst(30);
//        linkedList.addFirst(40);
//        String expectedResult = linkedList.toString();
//
//        System.out.println(myResult.equals(expectedResult));
//    }
//
//    @Test
//    public void bbaa() {
//        myLinkedList.addLast(10);
//        myLinkedList.addLast(20);
//        myLinkedList.addFirst(30);
//        myLinkedList.addFirst(40);
//        String myResult = myLinkedList.toString();
//
//        linkedList.addLast(10);
//        linkedList.addLast(20);
//        linkedList.addFirst(30);
//        linkedList.addFirst(40);
//        String expectedResult = linkedList.toString();
//
//        System.out.println(myResult.equals(expectedResult));
//    }
//
//    @Test
//    public void bbba() {
//        myLinkedList.addLast(10);
//        myLinkedList.addLast(20);
//        myLinkedList.addLast(30);
//        myLinkedList.addFirst(40);
//        String myResult = myLinkedList.toString();
//
//        linkedList.addLast(10);
//        linkedList.addLast(20);
//        linkedList.addLast(30);
//        linkedList.addFirst(40);
//        String expectedResult = linkedList.toString();
//
//        System.out.println(myResult.equals(expectedResult));
//    }
//
//    @Test
//    public void bbbb() {
//        myLinkedList.addLast(10);
//        myLinkedList.addLast(20);
//        myLinkedList.addLast(30);
//        myLinkedList.addLast(40);
//        String myResult = myLinkedList.toString();
//
//        linkedList.addLast(10);
//        linkedList.addLast(20);
//        linkedList.addLast(30);
//        linkedList.addLast(40);
//        String expectedResult = linkedList.toString();
//
//        System.out.println(myResult.equals(expectedResult));
//    }
//
//    @Test
//    public void abbb() {
//        myLinkedList.addFirst(10);
//        myLinkedList.addLast(20);
//        myLinkedList.addLast(30);
//        myLinkedList.addLast(40);
//        String myResult = myLinkedList.toString();
//
//        linkedList.addFirst(10);
//        linkedList.addLast(20);
//        linkedList.addLast(30);
//        linkedList.addLast(40);
//        String expectedResult = linkedList.toString();
//
//        System.out.println(myResult.equals(expectedResult));
//    }
//
//    @Test
//    public void aabb() {
//        myLinkedList.addFirst(10);
//        myLinkedList.addFirst(20);
//        myLinkedList.addLast(30);
//        myLinkedList.addLast(40);
//        String myResult = myLinkedList.toString();
//
//        linkedList.addFirst(10);
//        linkedList.addFirst(20);
//        linkedList.addLast(30);
//        linkedList.addLast(40);
//        String expectedResult = linkedList.toString();
//
//        System.out.println(myResult.equals(expectedResult));
//    }
//
//    @Test
//    public void aaab() {
//        myLinkedList.addFirst(10);
//        myLinkedList.addFirst(20);
//        myLinkedList.addFirst(30);
//        myLinkedList.addLast(40);
//        String myResult = myLinkedList.toString();
//
//        linkedList.addFirst(10);
//        linkedList.addFirst(20);
//        linkedList.addFirst(30);
//        linkedList.addLast(40);
//        String expectedResult = linkedList.toString();
//
//        System.out.println(myResult.equals(expectedResult));
//    }
//
//    @Test
//    public void abba() {
//        myLinkedList.addFirst(10);
//        myLinkedList.addLast(20);
//        myLinkedList.addLast(30);
//        myLinkedList.addFirst(40);
//        String myResult = myLinkedList.toString();
//
//        linkedList.addFirst(10);
//        linkedList.addLast(20);
//        linkedList.addLast(30);
//        linkedList.addFirst(40);
//        String expectedResult = linkedList.toString();
//
//        System.out.println(myResult.equals(expectedResult));
//    }
//
//    @Test
//    public void baab() {
//        myLinkedList.addLast(10);
//        myLinkedList.addFirst(20);
//        myLinkedList.addFirst(30);
//        myLinkedList.addLast(40);
//        String myResult = myLinkedList.toString();
//
//        linkedList.addLast(10);
//        linkedList.addFirst(20);
//        linkedList.addFirst(30);
//        linkedList.addLast(40);
//        String expectedResult = linkedList.toString();
//
//        System.out.println(myResult.equals(expectedResult));
//    }
//
//    @Test
//    public void abab() {
//        myLinkedList.addFirst(10);
//        myLinkedList.addLast(20);
//        myLinkedList.addFirst(30);
//        myLinkedList.addLast(40);
//        String myResult = myLinkedList.toString();
//
//        linkedList.addFirst(10);
//        linkedList.addLast(20);
//        linkedList.addFirst(30);
//        linkedList.addLast(40);
//        String expectedResult = linkedList.toString();
//
//        System.out.println(myResult.equals(expectedResult));
//    }
//
//    @Test
//    public void baba() {
//        myLinkedList.addLast(10);
//        myLinkedList.addFirst(20);
//        myLinkedList.addLast(30);
//        myLinkedList.addFirst(40);
//        String myResult = myLinkedList.toString();
//
//        linkedList.addLast(10);
//        linkedList.addFirst(20);
//        linkedList.addLast(30);
//        linkedList.addFirst(40);
//        String expectedResult = linkedList.toString();
//
//        System.out.println(myResult.equals(expectedResult));
//    }

}
