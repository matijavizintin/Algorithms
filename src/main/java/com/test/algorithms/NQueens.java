package com.test.algorithms;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Matija Vižintin
 * Date: 13. 09. 2015
 * Time: 19.47
 */
public class NQueens {

    public Set<List<Integer>> solve(int n) {
        Set<List<Integer>> result = com.google.common.collect.Sets.newTreeSet(new SetOfListsComparator());
        solveOneRec(n, Lists.newArrayList(), result);
        return result;
    }

    private void solveOneRec(int n, List<Integer> list, Set<List<Integer>> results) {
        if (n == list.size()) {
            results.add(list);        // combination found
            return;
        }

        for (int i = 0; i < n; i++) {
            boolean eval = eval(list, i);
            if (eval) {
                List<Integer> copy = Lists.newArrayList(list);
                copy.add(i);
                solveOneRec(n, copy, results);
            }
        }
    }

    private boolean eval(List<Integer> list, int next) {
        // save column
        if (list.contains(next)) return false;

        // check diagonal
        int nextRow = list.size();
        for (int i = 0; i < list.size(); i++) {
            int diagLeft = next - (nextRow - i);
            int diagRight = next + (nextRow - i);
            if (list.get(i) == diagLeft || list.get(i) == diagRight) return false;
        }
        return true;
    }

    public class SetOfListsComparator implements Comparator<List<Integer>> {

        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            Iterator<Integer> i1 = o1.iterator();
            Iterator<Integer> i2 = o2.iterator();

            while (true) {
                if (!i1.hasNext() && !i2.hasNext()) {
                    return 0;
                } else if (!i1.hasNext()) {
                    return 1;
                } else if (!i2.hasNext()) {
                    return -1;
                } else {
                    int cmp = new ListComparator().compare(i1.next(), i2.next());
                    if (cmp != 0) return cmp;
                }
            }

        }
    }

    public class ListComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }
}
