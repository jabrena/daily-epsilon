/// usr/bin/env jbang "$0" "$@" ; exit $?
// DEPS org.assertj:assertj-core:3.21.0

package edu.jab.math;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Problem20220130 {

    public static void main(String[] args) {

        record Combination(Long x, Long y, Long z) {}

        Function<Integer, Stream<Combination>> getCombinations =
                number -> LongStream.rangeClosed(1, number)
                                .mapToObj(x -> LongStream.rangeClosed(1, number)
                                                        .mapToObj(y -> LongStream.rangeClosed(1, number)
                                                                                .mapToObj(z -> new Combination(x, y, z))))
                                                        .flatMap(y -> y)
                                .flatMap(x -> x);

        Predicate<Combination> sum10 = element -> (element.x + element.y + element.z == 10) ? true : false;

        Predicate<Combination> distintNumbers =
                element -> ((element.x != element.y)
                         && (element.x != element.z)
                         && (element.y != element.z)) ? true : false;

        var solution = Stream.of(10)
                        .flatMap(getCombinations)
                        .filter(sum10.and(distintNumbers))
                        .map(element -> element.x * element.y * element.z)
                        .max(Long::compareTo)
                        .orElseThrow();

        System.out.println(solution);
        assertThat(solution).isEqualTo(30);
    }
}
