package com.tsystems.javaschool.tasks.subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
