package org.henrique.matheus;

public class Princial {
    public static void main(String[] args) {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        String trim;

        graph.addEdgeWeight("A", "B", 5.0);
        graph.addEdgeWeight("B", "C", 3.0);
        trim = graph.toDigraph().trim();
        System.out.println(trim);
    }
}
