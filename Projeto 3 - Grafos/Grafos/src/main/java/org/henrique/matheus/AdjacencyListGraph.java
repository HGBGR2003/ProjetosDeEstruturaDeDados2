package org.henrique.matheus;
import java.util.*;
public class AdjacencyListGraph<V> implements Graph<V> {
    private final Map<V, List<V>> adj = new HashMap<>();

    @Override
    public void addVertex(V v) {
        adj.putIfAbsent(v, new ArrayList<>());
    }

    @Override
    public void addEdge(V u, V v) {
        if (!adj.containsKey(u) || !adj.containsKey(v)) {
            throw new IllegalArgumentException("Vertex not found: " + u + " or " + v);
        }
        if (!adj.get(u).contains(v)) {
            adj.get(u).add(v);
            if (!u.equals(v)) {
                adj.get(v).add(u);
            }
        }
    }

    @Override
    public int countSelfLoops() {
        int count = 0;
        for (Map.Entry<V, List<V>> entry : adj.entrySet()) {
            V u = entry.getKey();
            for (V v : entry.getValue()) {
                if (u.equals(v)) count++;
            }
        }
        return count;
    }

    @Override
    public boolean isComplete() {
        int n = adj.size();
        for (Map.Entry<V, List<V>> entry : adj.entrySet()) {
            V u = entry.getKey();
            Set<V> neighbors = new HashSet<>(entry.getValue());
            // remove self-loops from neighbor count for completeness check
            neighbors.remove(u);
            // each vertex should connect to all others
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
        for (V u : adj.get(v)) {
            degree++;
            if (u.equals(v)) {
                // self-loop contributes an extra degree
                degree++;
            }
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
            if (u.equals(to)) break;
            for (V neigh : adj.get(u)) {
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
        for (Map.Entry<V, List<V>> entry : adj.entrySet()) {
            V u = entry.getKey();
            if (entry.getValue().isEmpty()) {
                sb.append("  \"").append(u).append("\";\n");
            }
            for (V v : entry.getValue()) {
                String edge;
                if (u.equals(v)) {
                    edge = "\"" + u + "\" -- \"" + u + "\""; // self-loop
                } else {
                    String a = u.toString(), b = v.toString();
                    if (a.compareTo(b) <= 0) {
                        edge = "\"" + a + "\" -- \"" + b + "\"";
                    } else {
                        edge = "\"" + b + "\" -- \"" + a + "\"";
                    }
                }
                if (seen.add(edge)) {
                    sb.append("  ").append(edge).append(";\n");
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
}