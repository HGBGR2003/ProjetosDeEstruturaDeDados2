import org.henrique.matheus.AdjacencyListGraph;
import org.henrique.matheus.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class GraphTest {
    private Graph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new AdjacencyListGraph<>();
    }

    @Test
    void testAddVertexAndEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        assertEquals(1, graph.degreeOf("A"));
        assertEquals(1, graph.degreeOf("B"));
    }

    @Test
    void testCountSelfLoops() {
        graph.addVertex("X");
        graph.addEdge("X", "X");
        assertEquals(1, graph.countSelfLoops());
    }

    @Test
    void testIsComplete() {
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");
        graph.addEdge("2", "3");
        assertTrue(graph.isComplete());
        graph.addVertex("4");
        assertFalse(graph.isComplete());
    }

    @Test
    void testDegreeOfWithSelfLoop() {
        graph.addVertex("V");
        graph.addEdge("V", "V");
        assertEquals(2, graph.degreeOf("V"));
    }

    @Test
    void testFindPathExists() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        List<String> path = graph.findPath("A", "C");
        assertEquals(Arrays.asList("A", "B", "C"), path);
    }

    @Test
    void testFindPathNotExists() {
        graph.addVertex("A");
        graph.addVertex("B");
        assertTrue(graph.findPath("A", "B").isEmpty());
    }

    @Test
    void testToDot() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        String dot = graph.toDot();
        assertTrue(dot.contains("\"A\" -- \"B\""));
        assertTrue(dot.startsWith("graph G"));
    }

    @Test
    void testDegreeOfIsolated() {
        graph.addVertex("Z");
        assertEquals(0, graph.degreeOf("Z"));
    }
    @Test
    void testAddDuplicateVertexAndEdge() {
        graph.addVertex("A");
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.addEdge("A", "B");
        assertEquals(1, graph.degreeOf("A"));
        assertEquals(1, graph.degreeOf("B"));
    }
    @Test
    void testAddEdgeMissingVertex() {
        graph.addVertex("A");
        assertThrows(IllegalArgumentException.class,
                () -> graph.addEdge("A", "X"));
    }

    @Test
    @DisplayName ("Verificação com um grafo de 5 vértices.")
    void testIsDone(){
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "E");
        assertTrue(graph.isComplete()); 
    }

    @Test
    @DisplayName ("Exemplo de verificação do caminho de um vertice.")
    void pathDone(){
        //Continuo depois.
    }

}
