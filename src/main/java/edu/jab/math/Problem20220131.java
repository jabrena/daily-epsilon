/// usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.assertj:assertj-core:3.21.0

package edu.jab.math;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Problem20220131 {

    public static void main(String[] args) {
        BiPredicate<Integer, Integer> isMultiple = (l, i) -> l % i == 0;
        Predicate<Integer> isMultiple5 = number -> isMultiple.test(number, 5);
        Predicate<Integer> isMultiple7 = number -> isMultiple.test(number, 7);

        var solution = IntStream.range(1, 100).boxed().filter(isMultiple5.or(isMultiple7)).count();

        System.out.println(solution);
        assertThat(solution).isEqualTo(31);
    }
}
