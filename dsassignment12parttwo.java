// Name: Chidera Anamege


import java.util.*;

// Represents an edge in the graph
class Edge implements Comparable<Edge> {
    char node1; // First node of the edge
    char node2; // Second node of the edge
    int weight; // Weight/cost of the edge

    // Constructor to initialize the edge
    public Edge(char node1, char node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    // Compare edges based on their weights (for sorting)
    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }

    // Return string representation of the edge
    @Override
    public String toString() {
        return "(" + node1 + "-" + node2 + ":" + weight + ")";
    }
}

public class dsassignment12parttwo {

    // Method to find Minimum Spanning Tree (MST) using Kruskal's algorithm
    public List<Edge> kruskalMST(List<Edge> edges, Set<Character> nodes) {
        List<Edge> mst = new ArrayList<>(); // List to store MST edges
        Collections.sort(edges); // Sort edges by weight (ascending order)

        Map<Character, Set<Character>> sets = new HashMap<>(); // Disjoint set to track connected components
        // Initialize each node as a separate set in the disjoint set
        for (char node : nodes) {
            sets.put(node, new HashSet<>(Collections.singleton(node)));
        }

        // Process each edge in sorted order
        for (Edge edge : edges) {
            char node1 = edge.node1;
            char node2 = edge.node2;
            Set<Character> set1 = sets.get(node1); // Find set of node1
            Set<Character> set2 = sets.get(node2); // Find set of node2

            // Check if adding this edge creates a cycle (nodes are in different sets)
            if (!set1.equals(set2)) {
                mst.add(edge); // Add edge to MST
                set1.addAll(set2); // Union sets containing node1 and node2
                // Update sets for all nodes in set2 to point to set1
                for (char node : set2) {
                    sets.put(node, set1);
                }
            }
        }

        return mst; // Return the Minimum Spanning Tree (MST)
    }

    public static void main(String[] args) {
        // Define edges of the graph with their weights
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge('A', 'B', 7));
        edges.add(new Edge('A', 'D', 5));
        edges.add(new Edge('A', 'E', 8));
        edges.add(new Edge('B', 'C', 8));
        edges.add(new Edge('B', 'D', 9));
        edges.add(new Edge('B', 'E', 7));
        edges.add(new Edge('C', 'E', 6));
        edges.add(new Edge('C', 'F', 4));
        edges.add(new Edge('C', 'G', 3));
        edges.add(new Edge('D', 'E', 2));
        edges.add(new Edge('D', 'F', 7));
        edges.add(new Edge('D', 'G', 3));
        edges.add(new Edge('E', 'F', 8));
        edges.add(new Edge('F', 'G', 7));
        edges.add(new Edge('G', 'H', 2));

        // Define nodes (vertices) of the graph
        Set<Character> nodes = new HashSet<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'));

        // Create an instance of the class to run Kruskal's algorithm
        dsassignment12parttwo kruskal = new dsassignment12parttwo();

        // Find Minimum Spanning Tree (MST) using Kruskal's algorithm
        List<Edge> mst = kruskal.kruskalMST(edges, nodes);

        // Calculate the total weight of the MST
        int totalWeight = 0;
        for (Edge edge : mst) {
            totalWeight += edge.weight;
        }

        // Output the MST edges and total weight
        System.out.println("MST: " + mst);
        System.out.println("Total weight: " + totalWeight);
    }
}
