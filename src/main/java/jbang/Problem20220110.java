/// usr/bin/env jbang "$0" "$@" ; exit $?
// DEPS org.assertj:assertj-core:3.21.0
// DEPS org.apache.commons:commons-math3:3.6.1

package jbang;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem20220110 {

    public static void main(String[] args) {

        Function<Long, BigDecimal> factorial =
                limit ->
                        IntStream.iterate(limit.intValue(), i -> i - 1)
                                .limit(limit)
                                .mapToObj(BigDecimal::valueOf)
                                .reduce(BigDecimal.ONE, BigDecimal::multiply);

        record Solution2(Integer x, Integer y) {}

        var iterations = 1000;
        IntStream.range(1, iterations)
                .skip(1)
                .limit(10)
                .mapToObj(x -> {
                    return IntStream.range(1, iterations)
                        .mapToObj(y -> {
                            return new Solution2(x, y);
                        });
                })
                .forEach(System.out::println);

        // System.out.println(compute.get());

    }
}
