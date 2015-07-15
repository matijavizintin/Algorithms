package com.test.algorithms;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Matija Vižintin
 * Date: 29. 05. 2015
 * Time: 15.30
 */
public class PowerSet implements Iterable<Set<Integer>> {
    private Collection<Integer> input;
    private int powerSetSize;

    public PowerSet(Collection<Integer> input) {
        this.input = input;
        this.powerSetSize = Double.valueOf(Math.pow(2., Integer.valueOf(input.size()).doubleValue())).intValue();
    }

    @Override public Iterator<Set<Integer>> iterator() {
        return new PowerSetIterator(input, powerSetSize);
    }
}

class PowerSetIterator implements Iterator<Set<Integer>> {
    private int powerSetSize;
    private int loop;
    private Integer[] inputArray;

    public PowerSetIterator(Collection<Integer> input, int powerSetSize) {
        // power set size is 2^n
        this.powerSetSize = powerSetSize;
        inputArray = input.toArray(new Integer[input.size()]);        // transform into array for fast access by position
    }

    @Override public boolean hasNext() {
        return loop < powerSetSize;
    }

    @Override public Set<Integer> next() {
        // transform into binary, all elements with 1 are added to subset
        String binary = Integer.toBinaryString(loop);
        int binarySize = binary.length();
        Set<Integer> subSet = new HashSet<>();
        for (int j = 0; j < binary.length(); j++) {
            if (binary.charAt(binarySize - j - 1) == '1') {
                subSet.add(inputArray[j]);
            }
        }
        loop++;
        return subSet;
    }
}
