package java8;

import java8.chap7.ForkJoinSumCalculator;
import org.junit.Test;

public class ForkJoinSumCalculatorTest {

    @Test
    public void testForkJoinSum() {
        System.out.println(ForkJoinSumCalculator.forkJoinSum(1_000_000));
    }
}
