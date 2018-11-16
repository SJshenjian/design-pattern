package jdk.INio.regex;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regular expression append/replace
 *
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/15
 */
public class RegexAppend {

    public static void main(String[] args) {
        String input = "Thanks you, thanks you very much";
        String regex = "([Tt])hanks";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            if (matcher.group(1).equals("T")) {
                matcher.appendReplacement(sb, "Thank");
            } else {
                matcher.appendReplacement(sb, "thank");
            }
        }

        matcher.appendTail(sb);
        System.out.println(sb);

        String replacement = "$1hank";
        sb.setLength(0);
        matcher.reset();

        while (matcher.find()) {
            matcher.appendReplacement(sb, replacement);
        }

        matcher.appendTail(sb);
        System.out.println(sb);

        replacement = "$0hank";
        System.out.println(matcher.replaceAll(replacement));
        System.out.println(input.replaceAll(regex, replacement));
    }
}
