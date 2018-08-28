package jdk.java8.chap13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 基本思路: 分治算法
 * 如{1, 4, 9}的子集可以划分为包含1和不包含1的两部分
 * 不包含1的子集很简单就是{4, 9}，
 * 包含1的子集可以通过将1插入到{4, 9}的各子集得到
 */
public class Subsets {

    public static void main(String[] args) {
        List<List<Integer>> subs = subsets(Arrays.asList(1, 4, 9));
        subs.forEach(System.out::println);
    }

    public static List<List<Integer>> subsets(List<Integer> list) {
        if (list.isEmpty()) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(Collections.EMPTY_LIST);
            return result;
        }

        int first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());

        List<List<Integer>> subWithoutFirst = subsets(rest);
        List<List<Integer>> subWithFirst = insertAll(first, subWithoutFirst);

        return concat(subWithFirst, subWithoutFirst);
    }

    public static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : lists) {
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }
        return result;
    }

    public static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
        List<List<Integer>> result = new ArrayList<>();
        result.addAll(a);
        result.addAll(b);
        return result;
    }

   /* []
            [9]
            [4]
            [4, 9]
            [1]
            [1, 9]
            [1, 4]
            [1, 4, 9]*/
}
