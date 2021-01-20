using System;
using System.Collections.Generic;
using System.IO;
class Solution {
    
    public static int WEIGHT = 6;
    
    public class Node{
        public int id;
        public int distance;
        public Queue<Node> neighbor;
        
        public Node(int id){
            this.id  = id;
            this.distance = -1; // NOT VISITED
            this.neighbor = new Queue<Node>();
        }
        
        public void addNeighbor(Node node){
            node.neighbor.Enqueue(this);
            this.neighbor.Enqueue(node);
        }
    }
    
    public static void findDistances(Node start){
        
        if(start == null)
            return;
        
        Queue<Node> queue = new Queue<Node>();
        
        start.distance = 0;
        
        queue.Enqueue(start);
        
        while( queue.Count > 0){
            Node current = queue.Dequeue();
            
            foreach(Node aux in current.neighbor){
            
                if(aux.distance==-1){
                    aux.distance = current.distance + WEIGHT;
                    queue.Enqueue(aux);
                }
            }
            
        }        
        
    }
    
    static void Main(String[] args) {
        int numQueries = Convert.ToInt32(Console.ReadLine());
        
        for(int q = 0; q < numQueries; q++){
            
            int[] arr = Array.ConvertAll(
                Console.ReadLine().Split(' '), 
                arrTemp => Convert.ToInt32(arrTemp));
            
            int numNodes = arr[0];
            int numEdges = arr[1];
            
            Node[] root = new Node[numNodes+1];
            root[0] = null;                    
            
            //  CREATE NODES
            for(int n = 1; n <= numNodes; n++){
                root[n] = new Node(n);    
            }
            
            //  CONNECT
            for(int e=0;e<numEdges;e++){
                int[] edges = Array.ConvertAll(
                Console.ReadLine().Split(' '), 
                arrTemp => Convert.ToInt32(arrTemp));
                
                root[edges[0]].addNeighbor(root[edges[1]]);
            }
            
            int start = Convert.ToInt32(Console.ReadLine());
                    
            Solution.findDistances(root[start]);
                    
            for(int i=1;i<=numNodes;i++){
                if(i!=start){
                    Console.Write(root[i].distance+" ");
                }
            }
            Console.WriteLine("");
        }
    }
}