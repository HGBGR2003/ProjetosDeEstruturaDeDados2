package org.henrique.matheus;

import java.util.List;
    public interface Graph<V> {
        void addVertex(V v);

        void addEdge(V u, V v);

        int countSelfLoops();

        boolean isComplete();

        int degreeOf(V v);

        /**
         * Find a path from one vertex to another using BFS.
         * @return ordered list of vertices in the path, or empty list if none.
         */
        List<V> findPath(V from, V to);

        /**
         * Export the graph in DOT format for Graphviz.
         * @return DOT representation string
         */
        String toDot();
    }


