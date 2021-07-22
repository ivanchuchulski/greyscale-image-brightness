package com.example;

import java.util.*;

public class Graph {
    private final Map<Node, List<Node>> nodeList;

    public Graph() {
        nodeList = new HashMap<>();
    }

    void addNode(Node node) {
        nodeList.put(node, new ArrayList<>());
    }

    public void addEdge(Node from, Node to) {
        if (!nodeList.containsKey(from)) {
            nodeList.put(from, new ArrayList<>());
        }
        List<Node> neighbours = nodeList.get(from);
        neighbours.add(to);
    }

    public List<Node> getNeighbours(Node node) {
        return nodeList.get(node);
    }

    public Set<Node> getAllNodes() {
        return new HashSet<>(nodeList.keySet());
    }
}
