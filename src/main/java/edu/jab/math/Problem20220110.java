/// usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.assertj:assertj-core:3.21.0
//DEPS org.apache.commons:commons-math3:3.6.1

package edu.jab.math;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Problem20220110 {

    public static void main(String[] args) {
        record Solution(Long x, Long y) {}

        Function<Integer, Stream<Solution>> crossProduct = number ->
            LongStream
                .rangeClosed(1, number)
                .mapToObj(x -> LongStream.rangeClosed(1, number).mapToObj(y -> new Solution(x, y)))
                .flatMap(x -> x);

        Function<Long, BigDecimal> factorial = limit ->
            IntStream
                .iterate(limit.intValue(), i -> i - 1)
                .limit(limit)
                .mapToObj(BigDecimal::valueOf)
                .reduce(BigDecimal.ONE, BigDecimal::multiply);

        Predicate<Solution> checkEquation = alternative -> {
            var left = factorial.apply(alternative.y).multiply(factorial.apply(alternative.y - 1));
            var right = factorial.apply(alternative.x);
            return left.compareTo(right) == 0;
        };

        Function<Integer, Solution> algorithm = complexity ->
            Stream
                .of(complexity)
                .flatMap(crossProduct) // Get all combinations
                .filter(checkEquation) // Check y!(y-1)! = x!
                .max(Comparator.comparing(Solution::x)) // Get Max
                .get();

        // Raw solution:
        var solution = algorithm.apply(100);
        System.out.println(solution);
        assertThat(solution).isEqualTo(new Solution(10L, 7L));

        // Algorithm complexity estimation
        record MetadataSolution(Integer complexity, Solution solution, Long processingTime) {}

        Function<Integer, MetadataSolution> processingTimeDecorator = complexity -> {
            Instant start = Instant.now();

            var result = algorithm.apply(complexity);

            Instant end = Instant.now();
            var processingTime = Duration.between(start, end).toMillis();

            return new MetadataSolution(complexity, result, processingTime);
        };

        var algorithmData = IntStream
            .iterate(100, i -> i + 100)
            .parallel()
            .limit(5)
            .mapToObj(x -> processingTimeDecorator.apply(x))
            .peek(System.out::println)
            .collect(Collectors.toUnmodifiableList());

        var regression = new SimpleRegression();

        // https://commons.apache.org/proper/commons-math/userguide/stat.html#a1.4_Simple_regression
        algorithmData.stream().forEach(obj -> regression.addData(obj.complexity, obj.processingTime));

        // Copy & Paste the equation to Desmos
        // https://www.desmos.com/calculator?lang=en
        System.out.println("y = " + regression.getIntercept() + " + " + regression.getSlope() + " x");
        System.out.println(regression.predict(1000));
    }
}
