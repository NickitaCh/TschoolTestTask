package com.tsystems.javaschool.tasks.subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Subsequence {
    public static void main(String[] args) {
        Subsequence s = new Subsequence();
        boolean b = s.find(Arrays.asList("A", "B", "C", "D"),
                Arrays.asList("BD", "A", "ABC", "B", "M", "D", "M", "C", "DC", "D"));
        System.out.println(b); // Result: true
    }

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")

    public boolean find(List x, List y) {
        // TODO: Implement the logic here
//        x = Stream.of(3, 9, 1, 5, 7).collect(toList());
//        y = Stream.of(1, 2, 3, 4, 5, 7, 9, 20).collect(toList());
        System.out.println(x);
        System.out.println(y);
        try {
            List temp = new ArrayList();
            int j = 0;
            for (Object o : x) {
                for (; j < y.size(); j++) {
                    if (o.equals(y.get(j))) {
                        temp.add(y.get(j));
                        j++;
                        break;
                    }
                }
            }
            return x.equals(temp);
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }
}
