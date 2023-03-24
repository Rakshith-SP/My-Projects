package projects.exercise.datastructures.trees;

public class AVLTree {
    private static class AVLNode {
        private final int value;
        private AVLNode leftChild;
        private AVLNode rightChild;
        private int height;

        public AVLNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node=" + value + " : h=" + height;
        }
    }

    private AVLNode rootNode;

    public void insert(int value) {
        rootNode = insert(rootNode, value);
    }


    private AVLNode insert(AVLNode node, int value) {
        if (node == null)
            return new AVLNode(value);

        if (value < node.value)
            node.leftChild = insert(node.leftChild, value);
        else if (value > node.value)
            node.rightChild = insert(node.rightChild, value);

        node.height = getHeight(node);
        return balance(node);
    }

    private AVLNode balance(AVLNode node) {
        if (isLeftHeavy(node)) {
            if (balanceFactor(node.leftChild) < 0)
                node.leftChild = rotateLeft(node.leftChild);
            return rotateRight(node);
        }
        else if (isRightHeavy(node)) {
            if (balanceFactor(node.rightChild) > 0)
                node.rightChild = rotateRight(node.rightChild);
            return rotateLeft(node);
        }
        return node;
    }


    private AVLNode rotateLeft(AVLNode root) {
        AVLNode newRoot = root.rightChild;

        root.rightChild = newRoot.leftChild;
        newRoot.leftChild = root;
        root.height = getHeight(root); //newRoot.height = getHeight(newRoot); ??
        return newRoot;
    }

    private AVLNode rotateRight(AVLNode root) {
        AVLNode newRoot = root.leftChild;

        root.leftChild = newRoot.rightChild;
        newRoot.rightChild = root;
        root.height = getHeight(root); //newRoot.height = getHeight(newRoot); ??
        return newRoot;
    }

    /* my solution, but doesn't do well in calculating height
    private void insert(AVLNode node, int value){
        if (value < node.value) {
            if (node.leftChild == null) {
                node.leftChild = new AVLNode(value);
                return;
            }
            insert(node.leftChild, value);
        }
        if (value > node.value) {
            if (node.rightChild == null) {
                node.rightChild = new AVLNode(value);
                return;
            }
            insert(node.rightChild, value);
        }
    }*/

    private int getHeight(AVLNode node) {
        if (node == null)
            return -1;

        if (isLeaf(node))
            return 0;

        return 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
    }
    private boolean isLeaf(AVLNode node) {
        return node.leftChild == null && node.rightChild == null;
    }
    private int balanceFactor(AVLNode node)  {
        return (node == null) ? 0 : getHeight(node.leftChild) - getHeight(node.rightChild);
    }
    private boolean isLeftHeavy(AVLNode node) {
        return balanceFactor(node) > 1;
    }
    private boolean isRightHeavy(AVLNode node) {
        return balanceFactor(node) < -1;
    }

}
