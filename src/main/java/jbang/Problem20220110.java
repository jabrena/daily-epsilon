/// usr/bin/env jbang "$0" "$@" ; exit $?
// DEPS org.assertj:assertj-core:3.21.0
// DEPS org.apache.commons:commons-math3:3.6.1

package jbang;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Problem20220110 {

    public static void main(String[] args) {

        Function<Long, BigDecimal> factorial =
                limit ->
                        IntStream.iterate(limit.intValue(), i -> i - 1)
                                .limit(limit)
                                .mapToObj(BigDecimal::valueOf)
                                .reduce((n1, n2) -> n1.multiply(n2))
                                .get();

        // System.out.println(result);

    }
}
