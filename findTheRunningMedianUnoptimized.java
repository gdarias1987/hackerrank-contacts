import java.io.*;
import java.util.*;

public class findTheRunningMedianUnoptimized {

    /*
     * Complete the runningMedian function below.
     */
    static double[] runningMedian(int[] a) {
        int cant = a.length;
        double[] store = new double[cant];
        List<Double> list = new ArrayList<Double>();
        
        for(int i = 0;i<cant;i++){
            list.add( Double.valueOf(a[i]));
            // list.add( new Double(a[i]) );

            Collections.sort(list);
            
            int auxSize = list.size();
            for(int j=0; j<auxSize;j++){
                
                    // EVEN 
                if( auxSize % 2 == 0 ){
                    double halfA = (auxSize/2)-1;
                    double halfB = halfA+1;
                    store[i] = ( list.get((int)halfA) + list.get((int)halfB) ) / 2;

                }
                    // ODD
                else{
                    if( auxSize == 1){
                        store[i] = list.get(0);
                    }
                    else{
                        double auxDob = Math.floor(auxSize/2);
                        int auxMid = (int) auxDob;
                        store[i] = list.get(auxMid);
                    }
                }
        
            }
        }
        
        return store;
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