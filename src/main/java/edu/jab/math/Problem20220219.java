/// usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.assertj:assertj-core:3.21.0

package edu.jab.math;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;

public class Problem20220219 {

    public static void main(String[] args) {
        var solution = IntStream
            .rangeClosed(10, 99)
            .boxed()
            .map(value -> value.toString().chars().filter(ch -> ch == '9').count())
            .reduce(0L, Long::sum);

        System.out.println(solution);
        assertThat(solution).isEqualTo(19);
    }
}
