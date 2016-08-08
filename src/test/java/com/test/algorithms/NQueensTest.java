package com.test.algorithms;

import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * Created by Matija Vižintin
 * Date: 13. 09. 2015
 * Time: 20.01
 */
public class NQueensTest {

    @Test
    public void simpleTest() {
        NQueens nQueens = new NQueens();
        long start = System.currentTimeMillis();
        Set<List<Integer>> solutions = nQueens.solve(5);
        long stop = System.currentTimeMillis();
        System.out.println(stop - start);

        System.out.println("Solutions: " + solutions.size());
        solutions.forEach(System.out::println);
    }
}
