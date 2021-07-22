package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution {
    private final int BORDER = 0;

    public void solve(short[][] matrix, int rows, int cols) {
        Graph graph = buildGraph(matrix, rows, cols);

        List<Region> regions = calculateRegions(graph, matrix);

        sortRegions(regions);

        printRegions(regions);
    }

    private Graph buildGraph(short[][] matrix, int rows, int cols) {
        Graph graph = new Graph();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == BORDER) {
                    continue;
                }
                Node currentNode = new Node(i, j);
                graph.addNode(currentNode);
                for (Node neighbour : findNeighbours(matrix, rows, cols, currentNode)) {
                    graph.addEdge(currentNode, neighbour);
                }
            }
        }
        return graph;
    }

    private List<Region> calculateRegions(Graph graph, short[][] matrix) {
        List<Region> regions = new ArrayList<>();
        Set<Node> allNodes = graph.getAllNodes();
        Set<Node> visited = new HashSet<>();

        while (!allNodes.isEmpty()) {
            Node startingNode = allNodes.stream().findFirst().orElse(null);
            Queue<Node> queue = new LinkedList<>();
            List<Node> currentRegion = new LinkedList<>();

            queue.add(startingNode);
            visited.add(startingNode);
            allNodes.remove(startingNode);

            while (!queue.isEmpty()) {
                Node currNode = queue.poll();
                currentRegion.add(currNode);

                List<Node> neighboursOfCurrent = graph.getNeighbours(currNode);

                for (Node neighbour : neighboursOfCurrent) {
                    if (!visited.contains(neighbour)) {
                        visited.add(neighbour);
                        allNodes.remove(neighbour);
                        queue.add(neighbour);
                    }
                }
            }

            double regionAverage = currentRegion.stream()
                    .mapToDouble((Node nodeInRegion) -> matrix[nodeInRegion.row()][nodeInRegion.col()])
                    .average().orElse(Double.NaN);

            regions.add(new Region(currentRegion.get(0), regionAverage));
        }

        return regions;
    }

    private void sortRegions(List<Region> regions) {
        regions.sort(Comparator.comparingDouble(Region::averageBrightness));
    }

    private void printRegions(List<Region> regions) {
        for (Region region : regions) {
            System.out.println("printing regions...");
            System.out.println(region.insideNode());
            System.out.println(region.averageBrightness());
        }
    }

    private List<Node> findNeighbours(short[][] matrix, int rows, int cols, Node currentNode) {
        List<Node> neighbours = new ArrayList<>();

        for (int drows = -1; drows <= 1; drows++) {
            for (int dcols = -1; dcols <= 1; dcols++) {
                if (drows == 0 && dcols == 0) {
                    continue;
                }

                Node neighbour = new Node(currentNode.row() + drows, currentNode.col() + dcols);

                if (neighbour.row() < 0 || neighbour.row() >= rows || neighbour.col() < 0 || neighbour.col() >= cols) {
                    continue;
                }

                if (matrix[neighbour.row()][neighbour.col()] == BORDER) {
                    continue;
                }

                neighbours.add(neighbour);
            }
        }

        return neighbours;
    }
}
