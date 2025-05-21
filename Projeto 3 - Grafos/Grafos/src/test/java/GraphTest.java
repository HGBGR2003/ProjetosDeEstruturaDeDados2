import org.henrique.matheus.AdjacencyListGraph;
import org.henrique.matheus.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public void testAddEdgeDefaultWeight() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        String dot = graph.toDot();

        assertTrue(dot.contains("\"A\" -- \"B\" [label=null]"),
                "Aresta A–B deveria ter peso padrão null");
    }

    @Test
    public void testAddEdgeWithCustomWeight() {
        double weight = 2.5;
        graph.addVertex("A");
        graph.addVertex("C");
        graph.addEdgeWeight("A", "C", weight, false);

        String dot = graph.toDot();

        assertTrue(dot.contains("\"A\" -- \"C\" [label=" + weight + "]"),
                "Aresta A–C deveria ter peso " + weight);
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
    @DisplayName("Verificação com um grafo de 5 vértices.")
    void testIsDone() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");
        assertFalse(graph.isComplete());

        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("A", "E");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "E");

        assertTrue(graph.isComplete());
    }

    @Test
    @DisplayName("Exemplo de verificação do caminho de um vertice.")
    void pathDone() {

        for (int i = 1; i <= 15; i++) {
            graph.addVertex("V" + i);
        }

        graph.addEdge("V1", "V2");
        graph.addEdge("V2", "V3");
        graph.addEdge("V3", "V4");
        graph.addEdge("V4", "V5");
        graph.addEdge("V5", "V6");
        graph.addEdge("V6", "V7");
        graph.addEdge("V7", "V8");
        graph.addEdge("V8", "V9");
        graph.addEdge("V9", "V10");
        graph.addEdge("V10", "V11");
        graph.addEdge("V11", "V12");
        graph.addEdge("V12", "V13");
        graph.addEdge("V13", "V14");
        graph.addEdge("V14", "V15");

        List<String> expectedPath = Arrays.asList(
                "V1", "V2", "V3", "V4", "V5",
                "V6", "V7", "V8", "V9", "V10",
                "V11", "V12", "V13", "V14", "V15");

        List<String> actualPath = graph.findPath("V1", "V15");
        assertEquals(expectedPath, actualPath);
    }

    @Test
    @DisplayName("Teste estruturado com o caminho testando, o vazio entre elementos e se existe caminho")
    void pathDiference() {
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addVertex("7");
        graph.addVertex("8");

        graph.addEdge("1", "2");
        graph.addEdge("2", "3");
        graph.addEdge("1", "4");
        graph.addEdge("4", "2");
        graph.addEdge("5", "2");
        graph.addEdge("5", "3");
        graph.addEdge("6", "3");

        graph.addEdge("7", "8");

        List<String> path = graph.findPath("1", "6");

        assertEquals(Arrays.asList("1", "2", "3", "6"), path);
    }

    @Test
    @DisplayName("Código para achar o menor caminho.")
    public void testDijkstra() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdgeWeight("A", "B", 1.0, false);
        graph.addEdgeWeight("B", "C", 2.0, false);
        graph.addEdgeWeight("C", "D", 3.0, false);
        graph.addEdgeWeight("A", "D", 10.0, false);

        Map<String, Double> distances = graph.dijkstra("A");
        assertEquals(0.0, distances.get("A"));
        assertEquals(1.0, distances.get("B"));
        assertEquals(3.0, distances.get("C"));
        assertEquals(6.0, distances.get("D"));
    }

    @Test
    @DisplayName("Código para o menor caminho usando o algoritmo dijkstra")
    public void testNoPath() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");

        Map<String, Double> distances = graph.dijkstra("A");
        assertEquals(Double.MAX_VALUE, distances.get("B"));
    }

    @Test
    @DisplayName("Teste destinado a mostrar o dot.")
    public void testToDigraph() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdgeWeight("A", "B", 5.0, true);

        graph.addEdgeWeight("B", "C", 3.0, true);
        graph.addEdgeWeight("C", "A", 1.5, true);

        graph.addEdgeWeight("B", "D", 4.0, true);
        graph.addEdgeWeight("D", "E", 6.0, true);
        graph.addEdgeWeight("E", "B", 2.0, true);

        graph.addEdgeWeight("C", "C", 7.0, true);

        String expectedDot = "digraph G {\n" +
                "  \"A\" -> \"B\" [label=5.0];\n" +
                "  \"B\" -> \"C\" [label=3.0];\n" +
                "  \"B\" -> \"D\" [label=4.0];\n" +
                "  \"C\" -> \"A\" [label=1.5];\n" +
                "  \"C\" -> \"C\" [label=7.0];\n" +
                "  \"D\" -> \"E\" [label=6.0];\n" +
                "  \"E\" -> \"B\" [label=2.0];\n" +
                "   A;\n" + 
                "   B;\n" + 
                "   C;\n" + 
                "   D;\n" + 
                "   E;\n" +    
                "}";

        assertEquals(expectedDot.trim(), graph.toDigraph().trim());
    }
}
