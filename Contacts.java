import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
    public static class Node{
        
        int size;
        Map<Character,Node> children = new HashMap<Character,Node>();
        
        public Node(){
            size=1;
        }
    }

    public static class Trie{
        Node root;
        
        public Trie(){
            root = new Node();
        }
        
        public void addName(String name){
            Node current = root;
            
            for( int i=0; i<name.length(); i++){
                if ( current.children.containsKey(name.charAt(i) )){
                        // Move to node and increment occurences
                    current = current.children.get(name.charAt(i));
                    current.size++;
                }
                else{
                        // Make new node and update current
                    Node node = new Node();
                    current.children.put(name.charAt(i),node);
                    current = node;
                }
            }
        }
        
        public int findPartial(String name){
            Node current = root;
            for(int i=0;i<name.length();i++){
                if( ! current.children.containsKey(name.charAt(i) ) ){
                        // Key not found
                    return 0; 
                }
                else{
                        // Next char/node
                    current = current.children.get( name.charAt(i) );
                }
            }
            return current.size;
        }

    }
    
    static int[] contacts(String[][] queries) {
        Trie will = new Trie();
        int cant = queries.length;       
        List<Integer> datos = new ArrayList<Integer>();
        
        for(int i=0;i<cant;i++){
            if( queries[i][0].equals("add") ){
                will.addName(queries[i][1]);
            }
            else if( queries[i][0].equals("find") ){
                int aux = will.findPartial(queries[i][1]);
                datos.add(aux);
            }
        }
        
        return datos.stream().mapToInt(i->i).toArray();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int queriesRows = Integer.parseInt(scanner.nextLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
