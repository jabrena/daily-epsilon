/// usr/bin/env jbang "$0" "$@" ; exit $?
// DEPS org.assertj:assertj-core:3.21.0

package edu.jab.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Predicate;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

public class Problem20220128 {

    public static void main(String[] args) {

        //TODO: Does exist a library to express MathML into Java?
        Predicate<Long> checkEquation = x -> {
            var precision = new MathContext(20);
            var component1LeftPart = BigDecimal.ONE.divide(BigDecimal.valueOf(4), precision).multiply(BigDecimal.valueOf(x));
            var component2LeftPart = BigDecimal.ONE.divide(BigDecimal.valueOf(7), precision).multiply(BigDecimal.valueOf(x));
            var leftPart = component1LeftPart.multiply(component2LeftPart);
            var leftPartRounded = leftPart.setScale(0, RoundingMode.HALF_UP);
            var rightPart = BigDecimal.valueOf(x);

            return (leftPartRounded.compareTo(rightPart) == 0) ? true : false;
        };

        var solution = LongStream.rangeClosed(1, 1000).boxed()
                        .filter(checkEquation)
                        .limit(1)
                        .findFirst()
                        .orElseThrow();

        System.out.println(solution);
        assertThat(solution).isEqualTo(28);
    }
}
