import java.io.*;
import java.util.*;

public class SwapNodeAlgo {
    
    public static class Node {
        int index;
        int level;
        Node left;
        Node right;

        public Node(int index, int level, Node left, Node right) {
            this.level = level;
            this.index = index;
            this.left = left;
            this.right = right;
        }
    }
    
    public static void swapInOrder(Node node,int level, int swapIndex){
        if(node==null){
            return ;
        }
        
        swapInOrder(node.left,level+1,swapIndex);
        swapInOrder(node.right,level+1,swapIndex);
    
        if( level >= swapIndex && ( level % swapIndex ) == 0 ){
            Node temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
    }
    
    public static void printInOrder(Node node, List<Integer> list){
        if(node==null){
            return ;
        }
        
        printInOrder(node.left,list);
        list.add(node.index);
        printInOrder(node.right,list);
    }
    
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        int nN = indexes.length; // number of Nodes 
        int nQ = queries.length; // number of Queries 
        int[][] response = new int[nQ][nN];

        Node root = new Node(1, 1, null, null);
        Node aux = root;
        
        Queue<Node> list = new LinkedList<Node>();
        list.offer(root);
        
        int C = 0;
        
        // CREATE BINARY TREE
        while( C < nN ){
            aux = list.poll(); 

            // each Node has a left and right value
            int leftVal = indexes[C][0]; 
            int rightVal = indexes[C][1]; 
            
            // -1 represent a empty node; else, create a new node increasing level
            aux.left = (leftVal == -1) ? null : new Node(leftVal, aux.level+1,null,null);
            aux.right = (rightVal == -1) ? null : new Node(rightVal, aux.level+1,null,null);
            
            if( aux.left != null && aux.left.index != -1){
                list.offer(aux.left);
            }
            
            if( aux.right != null && aux.right.index != -1){
                list.offer(aux.right);
            }

            C++;
        }
        
        // AFTER CREATING THE BINARY TREE
        for(int i=0;i<nQ;i++){
            swapInOrder(root,1,queries[i]);
            List<Integer> auxList = new ArrayList<Integer>();
            printInOrder(root,auxList);
            response[i]= auxList.stream().mapToInt(r->r).toArray();
        }
        
        return response;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}