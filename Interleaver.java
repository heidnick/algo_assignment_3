/**
 * CISC 380
 * Algorithms Assignment 3
 *
 * Implements a dynamic programming solution to the Interleaving Strings problem.
 * 
 * @author Nick Heid Aaron Gartner
 * Due Date: 3/21/21
 */

public class Interleaver{

    private Boolean[][] storage;

    public Interleaver(){
        
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
        /*
            Solution: O(m+n)
            Explanation: 
                The function will run exactly m (the length of string x) plus n (the length string y amount of times), and will do 
                constant work otherwise. Only one recursive call will be made, and either i or j will be decremented until one reaches
                zero. To avoid index out of bounds errors, a second private recursive function is called when one string reaches index 0 and runs the remaining amount of
                times j or i have until they reach zero. Every time a recursive call is made, it will add an entry to the table, so it will
                fill the table appropriately.
        */

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

        storage = new Boolean[x.length()][y.length()];

        return isInterleaved(x, y, z, x.length()-1, y.length()-1);

    }//isInterleaved

    private Boolean isInterleaved(String x, String y, String z, int i, int j) {
        //base case -> either x or y is finished with checks so now pass to single interleaving recursion method
        if (i<0) {
            return singleInterleaved(y, z, j, true);
        }
        if (j<0) {
            return singleInterleaved(x, z, i, false);
        }

        //subproblems
        if (z.charAt(i + j + 1) == x.charAt(i) && z.charAt(i + j + 1) != y.charAt(j)) {
            storage[i][j] = true;
            return isInterleaved(x, y, z, i-1, j);
        }else if (z.charAt(i + j + 1) != x.charAt(i) && z.charAt(i + j + 1) == y.charAt(j)) {
            storage[i][j] = true;
            return isInterleaved(x, y, z, i, j-1);
        }else if (z.charAt(i + j + 1) == x.charAt(i) && z.charAt(i + j + 1) == y.charAt(j)) {
            storage[i][j] = true;
            return isInterleaved(x, y, z, i-1, j);
        }else {
            return false;
        }


    }

    private boolean singleInterleaved(String x_or_y, String z, int idx, boolean x_terminated) {
        int i;
        int j;
        if (x_terminated) {
            i = 0;
            j = idx;
        }else {
            j = 0;
            i = idx;
        }
        
        //base case -> idx out of bounds
        if (idx < 0) {
            return true;
        }
        if (x_or_y.charAt(idx) == z.charAt(idx)) {
            if (storage[i][j] != null) {
                storage[i][j] = false;
            }else {
                storage[i][j] = true;
            }
            return singleInterleaved(x_or_y, z, idx - 1, x_terminated);
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
        /*
            The solution for this problem has O(m + n) runtime. The private function will start at i = 0, with i being the last character
            of the x string, and j = 0, with j being the last character of the y string. The function will either increment i or j depending on 
            a check to see if the next position is not null in the boolean array, storage. If i+1 is not null, it will append either an x, y, or xy depending
            on if the value in the boolean array is true or false, (false represents two consecutive matching chars ex: x = "cat" y = "bat" z = "c(aa)btt" and
            will only contain one position in the table. Once i or j reaches the last possible index (last character in string x or y), then a loop will fill in
            the remaining characters to the result string, ex. if i = 2 and x = "cat", the length of y minus j amount of y's will be appended to the string and then
            returned. So in the worst case scenario, the function will run i amount of times and then loop j amount of times, hence O(m + n), with m and n representing
            the lengths of strings x and y.    
        */


        boolean result = isInterleaved(x, y, z);
        //Prints the entire table
        /*for (int i =0; i< x.length(); i++) {
            for (int j = 0; j<  y.length(); j++) {
                System.out.println("[" + i + "][" + j + "]: " + storage[i][j]);
            }
        }*/
        String solution = "";
        String answer = "";

        //String initialization conditionals
        //false if one string ends before the other
        if (storage[0][0] == false){
            if (z.charAt(0) == x.charAt(0)){
                answer = "xy";
            }else{
                answer = "yx";
            }
            //if characte is a part of x or y add the corresponding letter to our string
        }else if (y.charAt(0) == x.charAt(0)){
            answer = "y";
        }else if (z.charAt(0) == x.charAt(0)){
            answer = "x";
        }else {
            answer = "y";
        }
        if (result) {
            solution = getSolution(x, y, 0, 0, answer);
        }else {
            return null;
        }
        return solution;

    }

    private String getSolution(String x, String y, int i, int j, String answer){
        //base cases
        if (i+1 > x.length()-1){
            for (int k = j; k< y.length()-1; k++) {
                answer +="y";
            }

            return answer;
        }
        if (j + 1 > y.length()-1) {
            for (int k = i; k<x.length()-1; k++) {
                answer +="x";
            }

            return answer;
        }
        

        if (storage[i + 1][j] != null){
            if (storage[i + 1][j] == true) {
                answer += "x";
                return getSolution(x, y, i+1, j, answer);
            }else {
                answer += "xy";
                return getSolution(x, y, i+1, j, answer);
            }
        }else if (storage[i][j+1] != null){
            if (storage[i][j+1] == true) {
                answer += "y";
                return getSolution(x, y, i, j+1, answer);
            }else {
                answer += "xy";
                return getSolution(x, y, i, j+1, answer);
            }
        }else {
            return null;
        }
        

    }


}//class