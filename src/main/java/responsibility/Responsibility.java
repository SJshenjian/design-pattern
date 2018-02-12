package responsibility;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 责任链模式lambda精简版
 */
public class Responsibility {

    public static void ResponsibilityExample() {
       UnaryOperator<String> headerProcessing = text -> "From Raoul, Mario and Alan: " + text;
       UnaryOperator<String> spellCheckerProcessing = text -> text.replaceAll("labda", "lamba");

       Function<String, String> pipeLine = headerProcessing.andThen(spellCheckerProcessing);
       String result = pipeLine.apply("Aren't labdas really sexy?!!");

       System.out.println(result);
    }
}