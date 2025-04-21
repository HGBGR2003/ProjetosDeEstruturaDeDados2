package org.henrique.matheus;

public class Principal {
    public static void main(String[] args) {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("F");
        graph.addVertex("Z2");
        graph.addEdge("C", "D");
        graph.addEdge("C", "F");
        graph.addEdge("A", "Z2");
        System.out.println(graph.toDot());
    }
}
