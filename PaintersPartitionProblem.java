import java.util.*;

class PaintersPartitionProblem {

	public int paint(int A, int B, int[] C) {

        long low = getMax(C); // units to paint when there are infinte number of painters
        long high = getSum(C); // units to paint when there is only one painter

        while(low < high) {

            long mid = (low + high) / 2;

            // how many painters will it take if one can paint a max of mid units
            int n = getNumberOfPainters(C, mid);

            if(A < n) low = mid+1;
            else high = mid;
        }

        // get time using mid
        return getTimeForUnits(C, B, low);
    }

    int getTimeForUnits(int[] A, int B, long units) {

        long mod = 10000003;
        long temp = 0;
        long maxTime = 0, time = 0;
        for(int i = 0; i < A.length; i++) {
            if(temp + (long)A[i] <= units) {
                temp += (long)A[i];
                time += ((long)A[i] * (long)B);
            } else {
                maxTime = Math.max(maxTime, time);
                time = ((long)A[i] * (long)B);
                temp = (long)A[i];
            }
        }
        maxTime = Math.max(maxTime, time);
        return (int)(maxTime % mod);
    }

    int getNumberOfPainters(int[] A, long max) {

        int num = 1;
        long temp = 0;
        for(int i = 0; i < A.length; i++) {
            if(temp + (long)A[i] <= max) {
                temp += (long)A[i];
            } else {
                num++;
                temp = (long)A[i];
            }
        }
        return num;
    }
    
    long getMax(int[] A) {

        long max = 0;
        for(int num : A){
            max = Math.max(max, (long)num);
        }
        return max;
    }
    
    long getSum(int[] A) {
        long sum = 0;
        for(int num : A){
            sum += (long)num;
        }
        return sum;
    }
}