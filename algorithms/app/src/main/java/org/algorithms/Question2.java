package org.algorithms;

public class Question2 {


    // Driver method for testing
    public static void main(String[] args) {
        // Sample input
        int[][] input = {
                {1, 3, 1, 2, 3, 4, 4, 3, 5},
                {1, 1, 1, 1, 1, 1, 1}
        };

        // Call the method to remove duplicates
        int[][] output = removeDuplicates(input);

        // Print the output
        for (int[] ints : output) {
            System.out.print("[ ");
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println("]");
        }
    }

    // Method to remove duplicates from each row of a 2D array
    public static int[][] removeDuplicates(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {  // Iterate over each row
            int[] seen = new int[1000001];  // Array to track seen elements (assuming numbers are <= 1,000,000)

            for (int j = 0; j < arr[i].length; j++) {  // Iterate over each element in the row
                int current = arr[i][j];

                // If the element is seen for the first time, mark it
                if (seen[current] == 0) {
                    seen[current] = 1;
                } else {
                    // If the element has been seen before, mark it as a duplicate (replace with 0)
                    arr[i][j] = 0;
                }
            }
        }
        return arr;  // Return the modified array
    }
}
