import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
    // CONDITIONS:
    //    1) maxHeap.size() == minHeap.size()
    //    2) maxHeap.size() - 1 == minHeap.size() ( maxHeap bigger)

    // maxHeap keeps track of the SMALL numbers heap
    // maxHeap.peek() --> "biggest-of-smallest"
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); 
    
    // minHeap keeps track of the LARGE numbers
    // minHeap.peek() --> "smallest-of-biggest"
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();           
    
    private static void addNumber(int n){
            //FIRST TIME
        if(maxHeap.isEmpty()){
                // ( maxHeap bigger)
            maxHeap.add(n);
        }
        // EVEN to ODD (to be unbalanced)
        else if( maxHeap.size() == minHeap.size() ){
            if( n < minHeap.peek() ){
                // if n is smaller than "smallest-of-biggest"
                // added to small heap
                maxHeap.add(n);
            }
            else{
                // if n is bigger than "smallest-of-biggest"
                // added to large heap
                minHeap.add(n);
                // remove from "smallest-of-biggest" 
                // and added to small heap
                maxHeap.add(minHeap.remove());
            }
        }
        // ODD to EVEN -  (to be balanced)
        if( maxHeap.size() > minHeap.size() ){
            // if n is bigger than "biggest-of-smallest"
            if( n > maxHeap.peek() ){
                // added to large heap
                minHeap.add(n);
            }
            else{
                // if n is smaller than "biggest-of-smallest"
                // added to small heap
                maxHeap.add(n);
                // remove from "biggest-of-smallest"
                // and added to large heap
                minHeap.add(maxHeap.remove());
            }
        }
    }
                    
    
    private static double getMedian() {
        if (maxHeap.isEmpty()) {
            return 0;
        } else if (maxHeap.size() == minHeap.size()) {
            // EVEN
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else { 
            // ODD
            // maxHeap must have more elements than minHeap
            return maxHeap.peek();
        }
    }
    
    static double[] runningMedian(int[] a) {

        double[] resultArray = new double[a.length];
    
        for(int i=0;i<a.length;i++){
            
            addNumber(a[i]);
            resultArray[i] = getMedian(); 
            
        }
        
        return resultArray;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(scanner.nextLine().trim());

        int[] a = new int[aCount];

        for (int aItr = 0; aItr < aCount; aItr++) {
            int aItem = Integer.parseInt(scanner.nextLine().trim());
            a[aItr] = aItem;
        }

        double[] result = runningMedian(a);

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
