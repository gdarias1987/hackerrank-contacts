import java.util.*;

public class BFS_ShortestReachInGraph {
    
    // INPUT
    // 1 -> number of times it will create a diff binaryTree && diff output
    // 2 -> number of nudes - number of edges 
    //  2a -> with number of nodes, create the binaryTree
    //  2b -> with number of edges, create the connections
    // 3 -> (last input after entering edges) start node
    
    private static final int EDGE_WEIGHT = 6;
    
    public static class Node {
        public final int id; 
        
        // Stores distances (-1 if not visited)
        public int distance; 
        
        public HashSet<Node> neighbors;
        
        public Node (int id) {
            this.id = id;
            distance = -1;
            neighbors = new HashSet<Node>();
        }
        
        // Two ways connections "a->b" && "b->a"
        public void addNeighbor(Node neighbor) {
            neighbors.add(neighbor);
            neighbor.neighbors.add(this);
        }
    }
    
    private static void findDistances(Node start) {
        if (start == null) {
            return;
        }
        
        Queue<Node> deque = new LinkedList<Node>();

        start.distance = 0;
        
        deque.add(start);
        
        while (!deque.isEmpty()) {
            
            Node curr = deque.remove();
            
            for (Node neighbor : curr.neighbors) {
                if (neighbor.distance == -1) {
                    neighbor.distance = curr.distance + EDGE_WEIGHT;
                    deque.add(neighbor);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int numQueries = scan.nextInt();
        
        // Each query will create a different BinaryTree
        for (int q = 0; q < numQueries; q++) {
            int numNodes = scan.nextInt();
            int numEdges = scan.nextInt();
            
            // CREATE NODES
            Node [] node = new Node[numNodes + 1]; 
            node[0] = null;
            for (int i = 1; i <= numNodes; i++) {
                node[i] = new Node(i);
            }
            
            // ADD EDGES
            for (int i = 0; i < numEdges; i++) {
                int n1 = scan.nextInt();
                int n2 = scan.nextInt();
                node[n1].addNeighbor(node[n2]);
            }
            
            // FIND DISTANCES FROM STARTING NODE 
            int start = scan.nextInt();
            findDistances(node[start]);
            
            // PRINT DISTANCES
            for (int i = 1; i <= numNodes; i++) {
                if (i != start) {
                    System.out.print(node[i].distance + " ");
                }
            }
            System.out.println();
        }
        scan.close();
    }
}
