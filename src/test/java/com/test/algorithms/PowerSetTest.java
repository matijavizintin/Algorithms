package com.test.algorithms;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * Created by Matija Vižintin
 * Date: 29. 05. 2015
 * Time: 15.54
 */
public class PowerSetTest {

    @Test
    public void test() {
        IntStream.range(1, 28).forEach(this::makeTest);
    }

    int dummy = 0;
    private void makeTest(int length) {
        List<Integer> input = IntStream.range(0, length).boxed().collect(Collectors.toList());

        // test power set created all at once
        Stopwatch sw1 = Stopwatch.createStarted();
        for (Set<Integer> integers : length > 15 ? ImmutableList.<Set<Integer>>of() : Sets.powerSet(input)) {
            for (Integer integer : integers) {
                dummy += integer;        // dummy action
            }
        }
        long elapsed1 = sw1.stop().elapsed(TimeUnit.MILLISECONDS);

        // test power set as iterator
        Stopwatch sw2 = Stopwatch.createStarted();
        StreamSupport.stream(new PowerSet(input).spliterator(), false).forEach(i -> i.stream().forEach(j -> dummy += j));
        long elapsed2 = sw2.stop().elapsed(TimeUnit.MILLISECONDS);

        // test power set as iterator as parallel stream
        Stopwatch sw3 = Stopwatch.createStarted();
        StreamSupport.stream(new PowerSet(input).spliterator(), false).forEach(i -> i.stream().forEach(j -> dummy += j));
        long elapsed3 = sw3.stop().elapsed(TimeUnit.MILLISECONDS);



        // guava implementation
        Stopwatch sw4 = Stopwatch.createStarted();
        for (Set<Integer> integers : com.google.common.collect.Sets.powerSet(com.google.common.collect.Sets.newHashSet(input))) {
            for (Integer integer : integers) {
                dummy += integer;        // dummy action
            }
        }
        long elapsed4 = sw4.stop().elapsed(TimeUnit.MILLISECONDS);

        // print times
        System.out.printf(
                "Length: %d\n Whole at once: %d\n As iterator: %d\n As iterator, lopped in parallel: %d\n Guava power set: %d\n\n", length, elapsed1,
                elapsed2, elapsed3, elapsed4);
    }
}
