/// usr/bin/env jbang "$0" "$@" ; exit $?
// DEPS org.assertj:assertj-core:3.21.0
// DEPS org.apache.commons:commons-math3:3.6.1

package jbang;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Problem20220110 {

    public static void main(String[] args) {

        Function<Long, BigDecimal> factorial =
                limit ->
                        IntStream.iterate(limit.intValue(), i -> i - 1)
                                .limit(limit)
                                .mapToObj(BigDecimal::valueOf)
                                .reduce(BigDecimal.ONE, BigDecimal::multiply);

        record Solution(Long x, Long y) {}

        var iterations = 10;

        Instant start = Instant.now();

        var result = LongStream.rangeClosed(1, iterations)
                .mapToObj(x -> {
                    return LongStream.rangeClosed(1, iterations)
                        .mapToObj(y -> {
                            return new Solution(x, y);
                        });
                })
                .flatMap(x -> x)
                .filter(obj -> {
                    var left = factorial.apply(obj.y).multiply(factorial.apply(obj.y - 1));
                    var right = factorial.apply(obj.x);
                    return left.compareTo(right) == 0;
                })
                .peek(System.out::println)
                .collect(Collectors.toUnmodifiableList());

        Instant end = Instant.now();
        System.out.println(
                "Process time: " + Duration.between(start, end).toMillis() + " milliseconds");

        // System.out.println(compute.get());
    }
}
