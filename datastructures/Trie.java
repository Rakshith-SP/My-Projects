package projects.exercise.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {
    private static class Node {
        private final char value;
        private boolean isEndOfWord;
        private final HashMap<Character, Node> children = new HashMap<>();
        public Node(char value) {
            this.value = value;
        }

        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }
        public void addChild(char ch) {
            children.put(ch, new Node(ch));
        }
        public Node getChild(char ch) {
            return children.get(ch);
        }
        public Node[] getChildren() {
            return children.values().toArray(new Node[0]);
        }
        public boolean hasAnyChild() {
            return !children.isEmpty();
        }
        public void removeChild(char ch) {
            children.remove(ch);
        }
        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private final Node rootNode = new Node(' ');

    public void insert(String word) {
        Node currentNode = rootNode;
        for (char letter : word.toCharArray()) {
            if (!currentNode.hasChild(letter))
                currentNode.addChild(letter);
            currentNode = currentNode.getChild(letter);
        }
        currentNode.isEndOfWord = true;
    }

    public boolean contains(String word) {
        if (word == null)
            return false;

        Node currentNode = rootNode;
        for (char letter : word.toCharArray()){
            if (!currentNode.hasChild(letter))
                return false;
            currentNode = currentNode.getChild(letter);
        }
        return currentNode.isEndOfWord;
    }

    public void traverse() {
        traverse(rootNode);
    }
    private void traverse(Node root) {
        for (Node child : root.getChildren())
            traverse(child);
        System.out.println(root.value);
    }

    public void remove(String word) {
        if (word == null)
            return;

        remove(rootNode, word, 0);
    }
    private void remove(Node root, String word, int index) {
        if (index == word.length()) {
            root.isEndOfWord = false;
            return;
        }

        char ch = word.charAt(index);
        Node child = root.getChild(ch);
        if (child == null)
            return;

        remove(child, word, index+1);

        if (!child.hasAnyChild() && !child.isEndOfWord)
            root.removeChild(ch);

        /* my solution to remove
public void remove(String word) {
        if (word == null)
            throw new IllegalStateException();

        traverse(rootNode, word.toCharArray(), 0);
    }

private boolean traverse(Node root, char[] charArray, int count) {
        for (Node child : root.getChildren()) {
            if (count < charArray.length && charArray[count] == child.value) {
                var toRemove = traverse(child, charArray, ++count);
                if (toRemove) {
                    if (!child.hasAnyChild() && !child.isEndOfWord) {
                        root.removeChild(charArray[count-1]);
                        return true;
                    }
                }
                return false;
            }
        }
        if (count == charArray.length) {
            root.isEndOfWord = false;
            return true;
        }
        return false;
    }
*/
    }

    public List<String> findWords(String prefix) {
        if (prefix == null)
            return null;

        if (prefix.equals(""))
            return new ArrayList<>() {};

        List<String> wordsList = new ArrayList<>();
        Node lastNode = findLastNodeOf(prefix);
        if (lastNode != null && lastNode.isEndOfWord)
            wordsList.add(prefix);
        return findWords(lastNode, prefix, wordsList);
    }
    private Node findLastNodeOf(String prefix) {
        Node node = rootNode;
        for (char letter : prefix.toCharArray()) {
            Node child = node.getChild(letter);
            if (child == null)
                return null;
            node = child;
        }
        return node;
    }
    private List<String> findWords(Node root, String prefix, List<String> wordsList) {
        if (root == null)
            return wordsList;

        String currStr = prefix;
        for (Node child : root.getChildren()) {
            currStr += child.value;
            if (child.isEndOfWord) {
                wordsList.add(currStr);
            }
            wordsList = findWords(child, currStr, wordsList);
            currStr = currStr.substring(0, currStr.length() - 1);
        }
        return wordsList;

        /* mosh's implementation
    private void findWords(Node root, String prefix, List<String> wordsList) {
    if (root.isEndOfWord)
        words.add(prefix);
    for(Node child : root.getChildren())
        findWords(child, prefix + child.value, wordsList);
    }
    */
    }

    public boolean containsRecursive(String word) {
        if (word == null)
            return false;

        return containsRecursive(rootNode, word, 0);
    }

    private boolean containsRecursive(Node root, String word, int index) {
        if (index == word.length())
            return root.isEndOfWord;

        char letter = word.charAt(index);
        Node child = root.getChild(letter);
        if (child == null)
            return false;
        return containsRecursive(child, word, index + 1);
    }

    public int countWords() {
        if (!rootNode.hasAnyChild()) {
            return 0;
        }

        return countWords(rootNode, 0);
    }
    private int countWords(Node root, int count) {
        if (root == null)
            return count;

        for (Node child : root.getChildren()) {
            if (child.isEndOfWord)
                count++;
            count = countWords(child, count);
        }
        return count;
    }

    public String longestCommonPrefix(String word1, String word2) {
        if (word1 == null || word2 == null)
            return "";

        return longestCommonPrefix(rootNode, word1, word2, 0, "", "");
    }

    public String longestCommonPrefix(String word){
        return word;
    }

    private String longestCommonPrefix(Node root, String word1, String word2, int index, String currString, String finalString) {
        if (index == word1.length() || index == word2.length()) {
            return finalString;
        }

        char letter1 = word1.charAt(index);
        char letter2 = word2.charAt(index);

        Node child1 = root.getChild(letter1);
        Node child2 = root.getChild(letter2);

        if (child1 == null || child2 == null)
            return finalString;

        if (child1.value != child2.value) {
            return finalString;
        }

        currString+=letter1;
        if (child1.isEndOfWord)
            finalString = currString;

        return longestCommonPrefix(child1, word1, word2, index + 1, currString, finalString);
    }
}



/*
private List<String> autoComplete(Node root, String word, List<String> wordsList, int index, String currString) {
        Node child = null;
        if (wordsList.isEmpty()) {
            char ch = word.charAt(index);
            child = root.getChild(ch);
            if (child == null)
                return null;
            currString += child.value;
            if (child.value == word.charAt(word.length() - 1)) {
                wordsList = new ArrayList<>();
                wordsList.add(currString);
                root = child;
            }
        }
        if (!wordsList.isEmpty()) {
            if (root == null)
                return wordsList;
            for (Node node : root.getChildren()) {
                currString += node.value;
                if (node.isEndOfWord){
                    wordsList.add(currString);
                }
                wordsList = autoComplete(node, word, wordsList, index + 1, currString);
                currString = currString.substring(0, currString.length() - 1);
            }
        }
        if (wordsList.isEmpty()) {
            wordsList = autoComplete(child, word, wordsList, index + 1, currString);
        }
        return wordsList;
    }
*/