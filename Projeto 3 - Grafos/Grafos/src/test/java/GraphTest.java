
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class GraphTest {
    private Graph<String> graph;

    // @BeforeEach
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
        assertTrue(graph.isComplete());
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
}

private void assertEquals(int i, int path) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
}
private void assertTrue(boolean complete) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'assertTrue'");
}