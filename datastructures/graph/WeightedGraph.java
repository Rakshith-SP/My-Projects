package projects.exercise.datastructures.graph;

import java.util.*;

public class WeightedGraph {

    private class Node {
        private final String label;
        private final Map<Node, Edge> edges = new HashMap<>();

        public Node(String label) {
            this.label = label;
        }

        public void addEdge(Node to, int weight) {
            edges.put(to, new Edge(this, to, weight));
        }

        public List<Edge> getEdges() {
            List<Edge> edgeList = new ArrayList<>();
            for (Node node : edges.keySet())
                edgeList.add(edges.get(node));
            return edgeList;
        }

        public List<Node> getNeighbours() {
            List<Node> neighbours = new ArrayList<>();
            for (Edge edge : edges.values())
                neighbours.add(edge.to);
            return neighbours;
        }

        public int getWeight(Node toNode) {
            return edges.get(toNode).weight;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private class Edge {
        private final Node from;
        private final Node to;
        private final int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "->" + to;
        }
    }

    private class NodeEntry {
        private final Node node;
        private final int priority;

        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    Map<String, Node> nodes = new HashMap<>();

    public void addNode(String label) {
        if (label == null)
            return;

        Node node = new Node(label);
        nodes.putIfAbsent(label, node);
    }

    public void addEdge(String from, String to, int weight) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            return;

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public Path getShortestPath(String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        if (fromNode == null || toNode == null)
            throw new IllegalArgumentException();

        Map<Node, Integer> distances = new HashMap<>();
        Map<Node, Node> previousNodes = new HashMap<>();
        Set<Node> visited = new HashSet<>();
        PriorityQueue<NodeEntry> queue = new PriorityQueue<>(Comparator.comparingInt(ne -> ne.priority));
        queue.add(new NodeEntry(fromNode, 0));
        for (var node : nodes.values())
            distances.put(node, Integer.MAX_VALUE);
        distances.replace(fromNode, 0);

        while (!queue.isEmpty()) {
            var current = queue.remove().node;
            visited.add(current);

            for (var edge : current.getEdges()) {
                if (visited.contains(edge.to))
                    continue;

                var newDistance = distances.get(current) + edge.weight;
                if (newDistance < distances.get(edge.to)) {
                    distances.replace(edge.to, newDistance);
                    previousNodes.put(edge.to, current);
                    queue.add(new NodeEntry(edge.to, newDistance));
                }
            }
        }

        return buildPath(previousNodes, toNode);
    }

    private Path buildPath(Map<Node, Node> previousNodes, Node toNode) {

        Stack<Node> stack = new Stack<>();
        stack.push(toNode);
        var previous = previousNodes.get(toNode);
        while (previous != null) {
            stack.push(previous);
            previous = previousNodes.get(previous);
        }

        var path = new Path();
        while (!stack.isEmpty())
            path.add(stack.pop().label);

        return path;
    }

    public boolean hasCycle() {
        Set<Node> visited = new HashSet<>();

        for (Node node : nodes.values()) {
            if (!visited.contains(node) && hasCycle(node, null, visited))
                return true;
        }

        return false;
    }

    private boolean hasCycle(Node node, Node parent, Set<Node> visited) {
        visited.add(node);
        for (Edge edge : node.getEdges()) {
            if (edge.to == parent)
                continue;

            if (visited.contains(edge.to) || hasCycle(edge.to, node, visited))
                return true;
        }
        return false;
    }

    public WeightedGraph getMinSpanningTree() {
        // this implementation might have bugs, follow mosh's implementation
        WeightedGraph tree = new WeightedGraph();

        if (nodes.isEmpty())
            return tree;

        Node currNode = nodes.values().iterator().next();

        if (currNode.getEdges().isEmpty())
            return tree;

        tree.addNode(currNode.label);
        while (tree.nodes.size() < nodes.size()) {
            PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
            for (Edge edge : currNode.getEdges())
                if (!tree.containsNode(edge.to.label))
                    priorityQueue.add(edge);

            if (priorityQueue.isEmpty())
                return tree;

            Edge minEdge = priorityQueue.remove();
            Node nextNode = minEdge.to;

            tree.addNode(nextNode.label);
            tree.addEdge(currNode.label, nextNode.label, minEdge.weight);

            currNode = nextNode;
        }
        return tree;
    }

    public boolean containsNode(String label) {
        return nodes.containsKey(label);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String str = "";
        for (Node node : nodes.values()) {
            List<Edge> edges = node.getEdges();
            if (edges.isEmpty())
                stringBuilder.append(node);
            for (Edge edge : edges) {
                str = edge + " with weight " +edge.weight + "\n";
                stringBuilder.append(str);
            }
        }

        return stringBuilder.toString();
    }
}

/*
public String findShortest(String from, String to) {
    Node fromNode = nodes.get(from);
    Node toNode = nodes.get(to);
    if (fromNode == null || toNode == null)
        throw new IllegalArgumentException();
    Map<Node, Integer> distances = new HashMap<>();
    Map<Node, Node> previousNodes = new HashMap<>();
    Set<Node> all = new HashSet<>();
    Set<Node> visited = new HashSet<>();
    PriorityQueue<NodeEntry> queue = new PriorityQueue<>(Comparator.comparingInt(ne -> ne.priority));

    for (Node node : nodes.values()) {
        distances.put(node, Integer.MAX_VALUE);
        all.add(node);
    }
    distances.replace(fromNode, 0);
    all.remove(fromNode);
    visited.add(fromNode);

    Node currNode = fromNode;
    while (!all.isEmpty()) {
        for (Node neighbour : currNode.getNeighbours()) {
            if (visited.contains(neighbour))
                continue;

            int distance = getDistance(distances, currNode, neighbour);
            if (distances.get(neighbour) > distance) {
                distances.replace(neighbour, distance);
                previousNodes.put(neighbour, currNode);
                queue.add(new NodeEntry(neighbour, distance));
            }
        }
        currNode = queue.remove().node;
        all.remove(currNode);
        visited.add(currNode);
    }

    return buildPath(fromNode, toNode, distances, previousNodes);
}

private String buildPath(Node fromNode, Node toNode, Map<Node, Integer> distances, Map<Node, Node> previousNodes) {
    StringBuilder str = new StringBuilder();
    Stack<String> stack = new Stack<>();
    int distance = distances.get(toNode);
    Node node = toNode;
    while (true) {
        stack.add(node.label);
        if (previousNodes.get(node) == fromNode) {
            stack.add(fromNode.label);
            break;
        }
        node = previousNodes.get(node);
    }
    while (!stack.isEmpty()) {
        str.append(stack.pop());
        str.append("->");
    }
    str.append(distance);
    return str.toString();
}
*/