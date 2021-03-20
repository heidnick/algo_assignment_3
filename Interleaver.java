/**
 * CISC 380
 * Algorithms Assignment 3
 *
 * Implements a dynamic programming solution to the Interleaving Strings problem.
 * 
 * @author YOUR NAME HERE
 * Due Date: xx/xx/xx
 */

public class Interleaver{

    public Interleaver(){
        //YOUR CODE HERE
        
    }//constructor

    /**
     * Finds if the two strings x and y are an interleaving of string z
     * 
     * The string Z is an interleaving of X and Y if it can be obtained
     * by interleaving the characters in X and Y in a way that
     * maintains the left-to-right order of the c in X and Y:
     * 
     * @param x the first string that composes an interleaving
     * @param y the second string that composes an interleaving
     * @param z the string to check for an interleaving
     * @return True, if the string z is an interleaving of x and y. False otherwise.
     * 
     */
    public Boolean isInterleaved(String x, String y, String z){
        //error checks
        if (x == null || x.length() == 0) {
            return false;
        }
        if (y == null || y.length() == 0) {
            return false;
        }
        if (x.length() + y.length() != z.length() || z == null){
            return false;
        }

        return isInterleaved(x, y, z, x.length()-1, y.length()-1);

    }//isInterleaved

    private Boolean isInterleaved(String x, String y, String z, int i, int j) {
        //base case -> either x or y is finished with checks so now pass to single interleaving recursion method
        if (j<0) {
            //System.out.println("j is less than 0");
            //return true;
            return singleInterleaved(x, z, i);
        }
        if (i<0) {
            //System.out.println("i is less than 0");
            //return true;
            return singleInterleaved(y, z, j);
        }

        //subproblems
        if (z.charAt(i + j + 1) == x.charAt(i) && z.charAt(i + j + 1) != y.charAt(j)) {
            //System.out.println("case 1");
            return isInterleaved(x, y, z, i-1, j);
        }else if (z.charAt(i + j + 1) != x.charAt(i) && z.charAt(i + j + 1) == y.charAt(j)) {
            //System.out.println("case 2");
            return isInterleaved(x, y, z, i, j-1);
        }else if (z.charAt(i + j + 1) == x.charAt(i) && z.charAt(i + j + 1) == y.charAt(j)) {
            //System.out.println("case 3");
            return isInterleaved(x, y, z, i-1, j);
        }else {
            //System.out.println("false interleaving");
            return false;
        }


    }

    private boolean singleInterleaved(String x_or_y, String z, int idx) {
        //base case -> idx out of bounds
        if (idx < 0) {
            return true;
        }

        if (x_or_y.charAt(idx) == z.charAt(idx)) {
            return singleInterleaved(x_or_y, z, idx - 1);
        }else {
            return false;
        }
    }

    /**
     * Returns a string representation of the solution of the interleaved string problem.
     * 
     * The return value is a string whose length is equal to z. 
     * All characters in z are replaced by character "x" if they come from the string "x",
     * and all characters in z are replaced by the character "y" if they come from the string y.
     * 
     * For example, on an input of x = "ab", y = "cd", and z = "abcd", then the output shall be the string "xxyy".
     * 
     * @param x the first string that composes an interleaving
     * @param y the second string that composes an interleaving
     * @param z the string to check for an interleaving
     * @return A string representation of the solution
     */
    public String getSolution(String x, String y, String z){

        //YOUR CODE HERE

        return null;

    }

}//class