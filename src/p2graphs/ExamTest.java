package p2graphs;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ExamTest {

	@Test
	public void testEccentricity() {
		Graph_Ex_2022_10_31<Integer> graph = new Graph_Ex_2022_10_31<>(4);

		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);

		graph.addEdge(1, 2, 0.5);
		graph.addEdge(1, 3, 3);
		graph.addEdge(2, 3, 1);
		graph.addEdge(3, 4, 3);

		assertEquals(4.5, graph.eccentricity(1));
		assertEquals(graph.INFINITE, graph.eccentricity(2));
		assertEquals(graph.INFINITE, graph.eccentricity(3));
		assertEquals(graph.INFINITE, graph.eccentricity(4));

		try {
			graph.eccentricity(5);
			fail();
		} catch(ElementNotPresentException e) {
			assertNotEquals(null, e.getMessage());
			assertNotEquals(0, e.getMessage().length());
		}

		try {
			graph.eccentricity(null);
			fail();
		} catch(NullPointerException e) {
			assertNotEquals(null, e.getMessage());
			assertNotEquals(0, e.getMessage().length());
		}
	}

}