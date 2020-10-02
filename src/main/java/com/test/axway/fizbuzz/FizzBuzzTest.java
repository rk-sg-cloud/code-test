package com.test.axway.fizbuzz;

public class FizzBuzzTest {

    public static void printFizzBuzz(int number) {
       for(int i = 0; i <= number; i++) {
           if(i % 3 == 0 && i % 5 == 0) {
               System.out.println("FizzBuzz");
           } else if(i % 3 == 0) {
               System.out.println("Fizz");
           } else if(i % 5 == 0) {
               System.out.println("Buzz");
           }
       }
    }

    public static void main(String[] args) {
        printFizzBuzz(1000);
    }
}