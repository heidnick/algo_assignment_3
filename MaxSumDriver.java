
/**
 * CISC 380
 * Algorithms Assignment 3 EXTRA CREDIT
 * 
 * Driver file for the MaxSum class
 * Tests the maxSumSubarray method.
 * 
 * 
 *  This driver file contains a single example test case to get you started.
 *  You should include more test cases to ensure that your implemenetation works correctly.
 *  You do NOT need to submit this file.
 * 
 *@author Nick Heid Aaron Gartner
 * Due Date: 3/21/21
 */



public class MaxSumDriver{


    public static void main(String[] args){
        try{


            int[] array = {10,-5,40,10};
                                                      
            System.out.println( MaxSum.maxSumSubarray(array) );

            
            int[] a1 = {-1, -2, -3};
                                                      
            System.out.println( MaxSum.maxSumSubarray(a1) );

            int[] a2 = {5, 15, -30, 10,-5,40,10};
                                                      
            System.out.println( MaxSum.maxSumSubarray(a2) );

            int[] a3 = {-3, -2, -1};
                                                      
            System.out.println( MaxSum.maxSumSubarray(a3) );
        

        }catch( Exception e){
            e.printStackTrace();
        }
       
    }//main


}