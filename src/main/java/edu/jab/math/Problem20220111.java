/// usr/bin/env jbang "$0" "$@" ; exit $?
// DEPS org.assertj:assertj-core:3.21.0
// DEPS org.apache.commons:commons-math3:3.6.1

package edu.jab.math;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;
import java.util.stream.LongStream;

public class Problem20220111 {

    public static void main(String[] args) {

        Function<Long, BigDecimal> factorial =
                limit ->
                        LongStream.iterate(limit, i -> i - 1)
                                .limit(limit)
                                .mapToObj(BigDecimal::valueOf)
                                .reduce(BigDecimal.ONE, BigDecimal::multiply);

        var numerator =
                LongStream.rangeClosed(1, 5)
                        .boxed()
                        .map(factorial)
                        .reduce(BigDecimal.ONE, BigDecimal::multiply);
        var denominator = BigDecimal.valueOf(5).pow(5).subtract(BigDecimal.ONE);
        var solution = numerator.divide(denominator, RoundingMode.HALF_UP);

        System.out.println(solution);
        assertThat(solution)
                .usingComparator(BigDecimal::compareTo)
                .isEqualTo(BigDecimal.valueOf(11));
    }
}
