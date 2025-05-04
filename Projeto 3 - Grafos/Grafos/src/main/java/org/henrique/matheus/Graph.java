package org.henrique.matheus;

import java.util.List;
    public interface Graph<V> {
        void addVertex(V v);

        void addEdge(V u, V v);

        int countSelfLoops();

        boolean isComplete();

        int degreeOf(V v);
  
        List<V> findPath(V from, V to);

        String toDot();
    }


