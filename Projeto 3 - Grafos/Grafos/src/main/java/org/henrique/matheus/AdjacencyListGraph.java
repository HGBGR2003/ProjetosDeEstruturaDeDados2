package org.henrique.matheus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AdjacencyListGraph<V> implements Graph<V> {

    private final Map<V, Map<V, Double>> adj = new HashMap<>();

    public Map<V, Double> dijkstra(V start) {
        Map<V, Double> distances = new HashMap<>();
        PriorityQueue<Node<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.weight));
        Map<V, V> previous = new HashMap<>();

        for (V v : adj.keySet()) {
            distances.put(v, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);
        pq.add(new Node<>(start, 0.0));

        while (!pq.isEmpty()) {
            Node<V> current = pq.poll();
            V u = current.vertex;

            for (Map.Entry<V, Double> entry : adj.get(u).entrySet()) {
                V v = entry.getKey();
                double weight = entry.getValue();
                double newDist = distances.get(u) + weight;

                if (newDist < distances.get(v)) {
                    distances.put(v, newDist);
                    previous.put(v, u);
                    pq.add(new Node<>(v, newDist));
                }
            }
        }

        return distances;
    }

    private static class Node<V> {
        V vertex;
        double weight;

        public Node(V vertex, double weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    @Override
    public void addVertex(V v) {
        adj.putIfAbsent(v, new HashMap<>());
    }

    @Override
    public void addEdge(V u, V v) {
        addEdgeWeight(u, v, null);
    }

    @Override
    public void addEdgeWeight(V u, V v, Double w) {
        if (!adj.containsKey(u) || !adj.containsKey(v)) {
            throw new IllegalArgumentException("Vertex not found: " + u + " or " + v);
        }
        adj.get(u).put(v, w);
        if (!u.equals(v)) {
            adj.get(v).put(u, w); 
        }

    }

    @Override
    public int countSelfLoops() {
        int count = 0;
        for (Map.Entry<V, Map<V, Double>> entry : adj.entrySet()) {
            V u = entry.getKey();
            if (entry.getValue().containsKey(u)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean isComplete() {
        int n = adj.size();
        for (Map.Entry<V, Map<V, Double>> entry : adj.entrySet()) {
            V u = entry.getKey();
            Set<V> neighbors = new HashSet<>(entry.getValue().keySet());
            neighbors.remove(u);
            if (neighbors.size() != n - 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int degreeOf(V v) {
        if (!adj.containsKey(v)) {
            throw new IllegalArgumentException("Vertex not found: " + v);
        }
        int degree = 0;
        for (Map.Entry<V, Double> e : adj.get(v).entrySet()) {
            V u = e.getKey();
            degree += u.equals(v) ? 2 : 1;
        }
        return degree;
    }

    @Override
    public List<V> findPath(V from, V to) {
        if (!adj.containsKey(from) || !adj.containsKey(to)) {
            throw new IllegalArgumentException("Vertex not found: " + from + " or " + to);
        }
        Map<V, V> prev = new HashMap<>();
        Queue<V> queue = new LinkedList<>();
        queue.add(from);
        prev.put(from, null);

        while (!queue.isEmpty()) {
            V u = queue.poll();
            if (u.equals(to))
                break;

            for (V neigh : adj.get(u).keySet()) {
                if (!prev.containsKey(neigh)) {
                    prev.put(neigh, u);
                    queue.add(neigh);
                }
            }
        }

        if (!prev.containsKey(to)) {
            return Collections.emptyList();
        }
        LinkedList<V> path = new LinkedList<>();
        for (V at = to; at != null; at = prev.get(at)) {
            path.addFirst(at);
        }
        return path;
    }

    @Override
    public String toDot() {
        StringBuilder sb = new StringBuilder();
        sb.append("graph G {\n");
        Set<String> seen = new HashSet<>();

        for (Map.Entry<V, Map<V, Double>> entry : adj.entrySet()) {
            V u = entry.getKey();
            Map<V, Double> neighbors = entry.getValue();

            if (neighbors.isEmpty()) {
                sb.append("  \"").append(u).append("\";\n");
            }

            for (Map.Entry<V, Double> e : neighbors.entrySet()) {
                V v = e.getKey();
                Double w = e.getValue();
                String edge;
                if (u.equals(v)) {
                    edge = "\"" + u + "\" -- \"" + u + "\"";
                } else {
                    String a = u.toString(), b = v.toString();
                    if (a.compareTo(b) <= 0) {
                        edge = "\"" + a + "\" -- \"" + b + "\"";
                    } else {
                        edge = "\"" + b + "\" -- \"" + a + "\"";
                    }
                }
                if (seen.add(edge)) {
                    sb.append("  ").append(edge).append(" [label=").append(w).append("];\n");
                }
            }
        }

        sb.append("}\n");
        String dot = sb.toString();
        saveInFile(dot, "dot.txt");
        return dot;
    }

    @Override
    public String toDigraph() {
    StringBuilder sb = new StringBuilder();
    sb.append("digraph G {\n");

    for (Map.Entry<V, Map<V, Double>> entry : adj.entrySet()) {
        V u = entry.getKey();
        Map<V, Double> neighbors = entry.getValue();

        if (neighbors.isEmpty()) {
            sb.append("").append(u).append(";\n");
        }

        for (Map.Entry<V, Double> e : neighbors.entrySet()) {
            V v = e.getKey();
            Double w = e.getValue();
            String edge = u + " -> " + v;

            sb.append("  ").append(edge).append(" [label=").append(w).append("];\n");
        }
    }

    sb.append("}\n");
    String dot = sb.toString();
    saveInFile(dot, "dot.txt");
    return dot;
    }

    private static void saveInFile(String dot, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(dot);
            System.out.println("Dot salvo com sucesso em: " + fileName);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o dot: " + e.getMessage());
        }
    }
}