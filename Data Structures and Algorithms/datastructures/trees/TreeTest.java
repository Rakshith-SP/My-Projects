package projects.exercise.datastructures.trees;

import org.junit.jupiter.api.Test;

public class TreeTest {
    // These tests work when they are debugged individually
    AVLTree tree = new AVLTree();

    @Test
    public void set1() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
    }

    @Test
    public void set2() {
        tree.insert(5);
        tree.insert(10);
        tree.insert(3);
        tree.insert(12);
        tree.insert(15);
        tree.insert(14);
    }

    @Test
    public void set3() {
        tree.insert(12);
        tree.insert(3);
        tree.insert(9);
        tree.insert(4);
        tree.insert(6);
        tree.insert(2);
    }

    @Test
    public void rightSkewedTree() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
    }

    @Test
    public void leftSkewedTree() {
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
    }

    @Test
    public void leftRightOperation() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(7);
    }

    @Test
    public void rightLeftOperation() {
        tree.insert(5);
        tree.insert(10);
        tree.insert(7);
    }

    @Test
    public void exampleTree() {
        tree.insert(5);
        tree.insert(15);
        tree.insert(6);
        tree.insert(1);
        tree.insert(8);
        tree.insert(12);
        tree.insert(18);
        tree.insert(17);
    }
}
