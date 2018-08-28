package jdk.java8.chap6;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Prime {

    public static boolean isPrime(int candidate) {
        return IntStream.rangeClosed(2, candidate-1)
                .limit((long)Math.floor(Math.sqrt(candidate)) - 1)
                .noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrime(List<Integer> primes, Integer candidate) {
        double candidateRoot = Math.sqrt((double)candidate);
        return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(i -> candidate % i == 0);
    }

    private static <A> List<A> takeWhile(List<A> primes, Predicate<A> p) {
        int i = 0;
        for (A item : primes) {
            if (!p.test(item)) {
                return primes.subList(0, i);
            }
            i++;
        }
        return primes;
    }
}
