
/**
 * CISC 380
 * Algorithms Assignment 3 EXTRA CREDIT
 * 
 * 
 * Implements an iterative dynamic programming solution to find the subarray of maximum sum, of a given array of numbers.
 * 
 * @author YOUR NAME HERE
 * Due Date: xx/xx/xx
 * 
 */


public class MaxSum{

    /**
     * Returns the sum of the subarray with the maximum sum from the given array of integers.
     * 
     * 
     * @param a an array of integers
     * @return the sum of the subarray with the maximum sum.
     */
    public static int maxSumSubarray(int[] a){
		
        //YOUR CODE HERE
        if (a.length == 0 || a == null) {
            return 0;
        }
        if (a.length == 1) {
            return a[0];
        }

        int[] results = new int[a.length];

        int current_sum = a[0];
        //if one element return that element 
        
        if (current_sum >= 0) {
            results[0] = a[0];
        } else {
            results[0] = -1;
        }
        //Code for filling table
        for (int i=1; i<a.length; i++) {
            //if sum from adding element i non-neg and
            //increases max sum add new max sum to table
            if (current_sum + a[i] > 0) {
                current_sum += a[i];
                results[i] = current_sum;
            } else {
                current_sum = 0;
                results[i] = -1;
            }
        }
        //Code for retrieval from table
        return results[a.length-1];

    }//maxSubArray

}//class