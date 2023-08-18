package projects.exercise.datastructures.graph;

import java.util.*;

public class Graph {

    private class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private final HashMap<String, Node> nodes = new HashMap<>();
    private final HashMap<Node, List<Node>> adjacencyList = new HashMap<>();

    public void addNode(String label) {
        if (nodes.containsKey(label))
            throw new IllegalArgumentException();

        Node node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }
    public void addEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            throw new IllegalArgumentException();

        adjacencyList.get(fromNode).add(toNode);
    }

    public void removeNode(String label) {
        Node targetNode = nodes.get(label);
        if (targetNode == null)
            return;

        for (Node node : adjacencyList.keySet())
            adjacencyList.get(node).remove(targetNode);

        nodes.remove(label);
        adjacencyList.remove(targetNode);
    }

    public void removeEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            return;

        adjacencyList.get(fromNode).remove(toNode);
    }

    public void depthFirstRec(String from) {
        Node node = nodes.get(from);
        if (node == null)
            return;

        HashSet<Node> visitedNodes = new HashSet<>();
        depthFirstRec(node, visitedNodes);
    }
    private void depthFirstRec(Node node, HashSet<Node> visitedNodes) {
        if (node == null)
            return;

        System.out.println(node);
        visitedNodes.add(node);


        for (Node neighbour : adjacencyList.get(node))
            if (!visitedNodes.contains(neighbour))
                depthFirstRec(neighbour, visitedNodes);
    }

    public void depthFirstIter(String from) {
        Node node = nodes.get(from);
        if (node == null)
            return;

        Stack<Node> stack = new Stack<>();
        Set<Node> visitedNodes = new HashSet<>();

        stack.push(node);
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            if (visitedNodes.contains(currentNode))
                continue;
            System.out.println(currentNode);
            visitedNodes.add(currentNode);

            for (Node neighbour : adjacencyList.get(currentNode))
                if (!visitedNodes.contains(neighbour))
                    stack.push(neighbour);
        }
    }

    public void breadthFirst(String from) {
        Node node = nodes.get(from);

        if (node == null)
            return;

        Set<Node> visitedNodes = new HashSet<>();
        Queue<Node> queue = new ArrayDeque<>();

        queue.add(node);
        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            if (visitedNodes.contains(currentNode))
                continue;
            System.out.println(currentNode);
            visitedNodes.add(currentNode);
            for (Node neighbour : adjacencyList.get(currentNode))
                if (!visitedNodes.contains(neighbour))
                    queue.add(neighbour);
        }

    }

    public List<String> topologicalSort() {
        Set<Node> visitedNodes = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        for (Node node : nodes.values())
            topologicalSort(node, stack, visitedNodes);

        List<String> sorted = new ArrayList<>();
        while (!stack.isEmpty())
            sorted.add(stack.pop().label);
        return sorted;
    }
    private void topologicalSort(Node root, Stack<Node> stack, Set<Node> visitedNodes) {
        if (visitedNodes.contains(root))
            return;

        visitedNodes.add(root);

        for (Node neighbour : adjacencyList.get(root))
            topologicalSort(neighbour, stack, visitedNodes);
        stack.push(root);
    }

    public boolean hasCycle() {
        Set<Node> all = new HashSet<>(adjacencyList.keySet());
        Set<Node> visiting = new HashSet<>();
        Set<Node> visited = new HashSet<>();

        while (!all.isEmpty()) {
            Node currNode = all.iterator().next();

            if (hasCycle(currNode, all, visiting, visited))
                return true;
        }
        return false;
    }

    private boolean hasCycle(Node node, Set<Node> all, Set<Node> visiting, Set<Node> visited) {
        moveFromAllToVisiting(all, visiting, node);
        for (Node neighbour : adjacencyList.get(node)) {
            if (visited.contains(neighbour))
                continue;

            if (visiting.contains(neighbour))
                return true;

            if (hasCycle(neighbour, all, visiting, visited))
                return true;
        }
        moveFromVisitingToVisited(visiting, visited, node);
        return false;
    }

    private void moveFromAllToVisiting(Set<Node> all, Set<Node> visiting, Node node) {
        all.remove(node);
        visiting.add(node);
    }

    private void moveFromVisitingToVisited(Set<Node> visiting, Set<Node> visited, Node node) {
        visiting.remove(node);
        visited.add(node);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node source : adjacencyList.keySet()) {
            List<Node> targets = adjacencyList.get(source);
            if (!targets.isEmpty()) {
                String str = source + " is connected to " + targets + "\n";
                stringBuilder.append(str);
            }
            else
                stringBuilder.append(source + "\n");
        }
        return stringBuilder.toString();
    }
}
