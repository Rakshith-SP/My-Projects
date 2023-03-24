package projects.exercise.datastructures.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tree {
    private static class Node {
        private final int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node = " + value;
        }
    }

    private Node rootNode;
    private int size;

    public void insert(int value) {
        if (rootNode == null) {
            rootNode = new Node(value);
            size++;
            return;
        }
        Node currentNode = rootNode;
        while (true) {
            if (value < currentNode.value) {
                if (currentNode.leftChild == null) {
                    currentNode.leftChild = new Node(value);
                    size++;
                    return;
                }
                currentNode = currentNode.leftChild;
            }
            else if (value > currentNode.value) {
                if (currentNode.rightChild == null) {
                    currentNode.rightChild = new Node(value);
                    size++;
                    return;
                }
                currentNode = currentNode.rightChild;
            }
        }
    }

    public boolean find(int value) {
        Node currentNode = rootNode;
        while (currentNode != null) {
            if (value == currentNode.value)
                return true;
            else if (value < currentNode.value) {
                currentNode = currentNode.leftChild;
            }
            else {
                currentNode = currentNode.rightChild;
            }
        }
        return false;
    }

    public void traversePreOrder() {
        traversePreOrder(rootNode);
    }

    public void traverseInOrder() {
        traverseInOrder(rootNode);
    }

    public void traversePostOrder() {
        traversePostOrder(rootNode);
    }

    public int height() {
        return height(rootNode);
    }

    public int findMin() {
        if (rootNode == null)
            throw new IllegalStateException();

        return findMin(rootNode);
    }

    public boolean equals(Tree tree) {
        if (isLeaf(this.rootNode) || isLeaf(tree.rootNode))
            return false;

        return areNodesEqual(this.rootNode, tree.rootNode);
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(rootNode, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public void swap() {
        var tempNode = rootNode.leftChild;
        rootNode.leftChild = rootNode.rightChild;
        rootNode.rightChild = tempNode;
    }

    public ArrayList<Integer> getNodesAtDistance(int k) {
        ArrayList<Integer> list = new ArrayList<>();
        getNodesAtDistance(rootNode, k, list);
        return list;
    }

    public void levelOrderTraversal() {
        for (int i = 0; i <= height(); i++) {
            for (int number : getNodesAtDistance(i)) {
                System.out.println(number);
            }
        }
    }

    public int size() {
        return size;
    }

    public int countLeaves() {
        return countLeaves(rootNode);
    }

    public int findMaxForBST() {
        if (rootNode == null)
            throw new IllegalStateException();

        return findMaxForBST(rootNode);
    }

    public boolean contains(int value) {
        return contains(rootNode, value);
    }

    public boolean areSibling(int value1, int value2) {
        return areSibling(rootNode, value1, value2);
    }

    public List<Integer> getAncestors(int value) {
        if (rootNode == null)
            return null;

        List<Integer> ancestors = new ArrayList<>();
        getAncestors(rootNode, value, ancestors);
        if (ancestors.size() == 0)
            return null;
        Collections.reverse(ancestors); // comment this if you want the order to be reversed
        return ancestors;
    }

    public boolean isBalanced() {
        return isBalanced(rootNode);
    }

    public boolean isPerfect() {
        return size() == (Math.pow(2, height() + 1) - 1);
        /* // my solution
        int perfectSize = getPerfectSize(height());
        int currentSize = size();
        return (perfectSize == currentSize);
                        // a method
        private int getPerfectSize(int height) {
        if (height == 0) return 0;
        return (getPerfectSize(height-1)*2) + 1;}*/
    }

    private void traversePreOrder(Node root) {
        if (root == null)
            return;

        System.out.println(root.value);
        traversePreOrder(root.leftChild);
        traversePreOrder(root.rightChild);
    }
    private void traverseInOrder(Node root) {
        if (root == null)
            return;

        traverseInOrder(root.leftChild);
        System.out.println(root.value);
        traverseInOrder(root.rightChild);
    }
    private void traversePostOrder(Node root) {
        if (root == null)
            return;

        traversePostOrder(root.leftChild);
        traversePostOrder(root.rightChild);
        System.out.println(root.value);
    }
    private int height(Node root) {
        if (root == null)
            return -1;

        if (isLeaf(root))
            return 0;

        return 1 + Math.max(height(root.leftChild), height(root.rightChild));
    }
    private int findMin(Node root) {
        if (isLeaf(root))
            return root.value;

        if (root.leftChild == null)
            return Math.min(root.rightChild.value, root.value);
        if (root.rightChild == null)
            return Math.min(root.leftChild.value, root.value);

        return Math.min(root.value,
                        Math.min(findMin(root.leftChild),
                                 findMin(root.rightChild)));
    }
    private boolean isLeaf(Node node) {
        return node.leftChild == null && node.rightChild == null;
    }
    private boolean areNodesEqual(Node node1, Node node2) {
        if (node1 == null && node2 == null)
            return true;

        if (node1 != null && node2 != null) {
            return node1.value == node2.value
                    && areNodesEqual(node1.leftChild, node2.leftChild)
                    && areNodesEqual(node1.rightChild, node2.rightChild);
        }

        return false;

        /* // my solution
        if (node1!=null && node2!=null) {
            if (node1.value == node2.value) {
                if (isLeaf(node1) && isLeaf(node2))
                    return true;
                if (node1.leftChild != null && node2.leftChild != null)
                    return areNodesEqual(node1.leftChild, node2.leftChild);
                if (node1.rightChild != null && node2.rightChild != null)
                    return areNodesEqual(node1.rightChild, node2.rightChild);
            }
            else return false;
        }
        return false;*/
    }
    private boolean isBinarySearchTree(Node node, int min, int max) {
        if (node == null)
            return true;

        if (node.value > min && node.value < max)
            return isBinarySearchTree(node.leftChild, min, node.value)
                    && isBinarySearchTree(node.rightChild, node.value, max);
        return false;

        /* Mosh's solution
        if (rootNode == null)
            return true;
        if (rootNode.value < min || rootNode.value > max)
            return false;
        return isBinarySearchTree(rootNode.leftChild, min, rootNode.value - 1)
                && isBinarySearchTree(rootNode.rightChild, rootNode.value + 1, max);*/
    }
    private void getNodesAtDistance(Node node, int distance, ArrayList<Integer> list) {
        if (node == null)
            return;

        if (distance == 0) {
            list.add(node.value);
            return;
        }

        getNodesAtDistance(node.leftChild, distance-1, list);
        getNodesAtDistance(node.rightChild, distance-1, list);
    }
    private int countLeaves(Node node) {
        if (node == null)
            return 0;

        if (isLeaf(node))
            return 1;

        if (node.leftChild == null || node.rightChild == null)
            return 1;

        return countLeaves(node.leftChild) + countLeaves(node.rightChild);
    }
    private int findMaxForBST(Node node) {
        if (node.rightChild != null)
            return findMaxForBST(node.rightChild);

        return node.value;
    }
    private boolean contains(Node node, int value) {
        if (node == null)
            return false;

        if (node.value == value)
            return true;

        if (isLeaf(node))
            return false;

        if (node.rightChild == null)
            return contains(node.leftChild, value);

        if (node.leftChild == null)
            return contains(node.rightChild, value);

        return contains(node.leftChild, value) || contains(node.rightChild, value);

    }
    private boolean areSibling(Node node, int value1, int value2) {
        if (node == null) // if there is no node at all
            return false;

        if (isLeaf(node)) // if there are no children
            return false;

        if (node.leftChild != null && node.rightChild != null) { // if there exist both children
            if ((node.leftChild.value == value1 && node.rightChild.value == value2) || (node.rightChild.value == value1 && node.leftChild.value == value2))
                return true;
            else
                return areSibling(node.leftChild, value1, value2) || areSibling(node.rightChild, value1, value2);
        }

        if (node.leftChild != null) // if there is only left child
            if (areSibling(node.leftChild, value1, value2))
                return true;

        if (node.rightChild != null) // if there is only right child
            if (areSibling(node.rightChild, value1, value2))
                return true;

        return false;
    }
    private List<Integer> getAncestors(Node node, int value, List<Integer> ancestors) {
        if (node == null)
            return null;

        if (isLeaf(node))
            return null;

        if (node.rightChild == null)
            if (node.leftChild.value == value) {
                ancestors.add(node.value);
                return ancestors;
            }

        if (node.leftChild == null)
            if (node.rightChild.value == value) {
                ancestors.add(node.value);
                return ancestors;
            }

        if (node.leftChild != null && node.rightChild != null) {
            if (node.leftChild.value == value || node.rightChild.value == value) {
                ancestors.add(node.value);
                return ancestors;
            }
            List<Integer> tempList;
            tempList = getAncestors(node.leftChild, value, ancestors);
            if (tempList != null) {
                ancestors.add(node.value);
                return tempList;
            }
            tempList = getAncestors(node.rightChild, value, ancestors);
            if (tempList != null) {
                ancestors.add(node.value);
                return tempList;
            }
        }

        return null;
    }
    /* // mosh's solution - makes more sense
    private boolean getAncestors(Node root, int value, List<Integer> list) {
    // We should traverse the tree until we find the target value. If
    // find the target value, we return true without adding the current node
    // to the list; otherwise, if we ask for ancestors of 5, 5 will be also
    // added to the list.
    if (root == null)
      return false;

    if (root.value == value)
      return true;

    // If we find the target value in the left or right sub-trees, that means
    // the current node (root) is one of the ancestors. So we add it to the list.
    if (getAncestors(root.leftChild, value, list) ||
        getAncestors(root.rightChild, value, list)) {
      list.add(root.value);
      return true;
    }

    return false;
  }
    */
    private boolean isBalanced(Node node) {
        if (node == null)
            return true;

        return Math.abs(height(node.leftChild) - height(node.rightChild)) <= 1 &&
                isBalanced(node.leftChild) && isBalanced(node.rightChild);
    }

}
