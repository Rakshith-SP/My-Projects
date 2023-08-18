package projects.exercise.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private final List<String> nodes = new ArrayList<>();

    public void add(String node) {
        nodes.add(node);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String string : nodes) {
            str.append(string);
            str.append("->");
        }
        str.delete(str.length() - 2, str.length());
        return str.toString();
    }

}
