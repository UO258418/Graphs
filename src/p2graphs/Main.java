package p2graphs;

public class Main {

    public static void main(String... args) {
        Graph<String> g = new Graph<>(5);
        g.addNode("España");
        g.addNode("Francia");
        g.addNode("Andorra");
        g.addNode("Italia");
        g.addNode("Alemania");

        g.addEdge("España", "Andorra", 0.5);
        g.addEdge("Andorra", "Francia", 1.0);
        g.addEdge("Francia", "Alemania", 1.5);

        System.out.println(g.traverseGraphDF("España"));
    }

}
