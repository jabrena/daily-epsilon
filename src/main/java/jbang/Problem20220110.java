/// usr/bin/env jbang "$0" "$@" ; exit $?
// DEPS org.assertj:assertj-core:3.21.0
// DEPS org.apache.commons:commons-math3:3.6.1

package jbang;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Problem20220110 {

    public static void main(String[] args) {

        record Solution(Long x, Long y) {}

        Function<Integer, Stream<Solution>> crossProduct =
                number ->
                        LongStream.rangeClosed(1, number)
                                .mapToObj(
                                        x ->
                                                LongStream.rangeClosed(1, number)
                                                        .mapToObj(y -> new Solution(x, y)))
                                .flatMap(x -> x);

        Function<Long, BigDecimal> factorial =
                limit ->
                        IntStream.iterate(limit.intValue(), i -> i - 1)
                                .limit(limit)
                                .mapToObj(BigDecimal::valueOf)
                                .reduce(BigDecimal.ONE, BigDecimal::multiply);

        Predicate<Solution> checkEquation =
                alternative -> {
                    var left =
                            factorial
                                    .apply(alternative.y)
                                    .multiply(factorial.apply(alternative.y - 1));
                    var right = factorial.apply(alternative.x);
                    return left.compareTo(right) == 0;
                };

        Instant start = Instant.now();

        var iterations = 10;

        var result =
                Stream.of(iterations)
                        .flatMap(crossProduct) // Get all combinations
                        .filter(checkEquation) // Check y!(y-1)! = x!
                        .max(Comparator.comparing(Solution::x)) // Get Max
                        .get();

        System.out.println(result);

        Instant end = Instant.now();
        System.out.println(
                "Process time: " + Duration.between(start, end).toMillis() + " milliseconds");
    }
}
