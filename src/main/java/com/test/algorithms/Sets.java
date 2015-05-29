package com.test.algorithms;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Matija Vižintin
 * Date: 29. 05. 2015
 * Time: 15.30
 */
public class Sets {

    public static Set<Set<Integer>> powerSet(Collection<Integer> input) {
        // power set size is 2^n
        int powerSetSize = Double.valueOf(Math.pow(2., Integer.valueOf(input.size()).doubleValue())).intValue();
        Integer[] inputArray = input.toArray(new Integer[input.size()]);        // transform into array for fast access by position

        // create power set
        Set<Set<Integer>> powerSet = new HashSet<>();
        for (int i = 0; i < powerSetSize; i++) {
            // transform into binary, all elements with 1 are added to subset
            String binary = Integer.toBinaryString(i);
            int binarySize = binary.length();
            Set<Integer> subSet = new HashSet<>();
            for (int j = 0; j < binary.length(); j++) {
                if (binary.charAt(binarySize - j - 1) == '1') {
                    subSet.add(inputArray[j]);
                }
            }
            powerSet.add(subSet);       // add subset to power set
        }
        return powerSet;
    }

    public static void print(Set<Set<Integer>> powerSet) {
        System.out.println("Printing powerSet:");
        for (Set<Integer> subSet : powerSet) {
            System.out.println(subSet);
        }
        System.out.println("Sets printed.");
    }
}
