package org.algorithms;

public class Question3 {
    public static void main(String[] args) {
        int number = 12345;
        int sum = findSumOfDigits(number);
        System.out.println("Sum of digits: " + sum);
    }

    public static int findSumOfDigits(int number) {
        if (number == 0) {
            return 0;
        } else {
            return (number % 10) + findSumOfDigits(number / 10);
        }
    }
}
