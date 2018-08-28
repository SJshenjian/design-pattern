package jdk.java8.chap13;

import java.util.stream.IntStream;

public class Recursion {

    public static void main(String[] args) {
        System.out.println(factorialIterative(5));
        System.out.println(factorialRecursive(5));
        System.out.println(factorialStreams(5));
        System.out.println(factorialTailRecursive(5));
    }

    //尾递归仅用一个栈帧
    public static int factorialTailRecursive(int n) {
        return factorialHelper(1, n);
    }

    public static int factorialStreams(int n) {
        return IntStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b);
    }

    public static int factorialIterative(int n) {
        int r = 1;
        for (int i = 1; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    private static int factorialRecursive(int n) {
        return n == 1 ? 1 : n * factorialIterative(n - 1);
    }

    private static int factorialHelper(int acc, int n) {
        return n == 1 ? acc : factorialHelper(acc * n, n - 1);
    }
}
