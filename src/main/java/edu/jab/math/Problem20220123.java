/// usr/bin/env jbang "$0" "$@" ; exit $?
// DEPS org.assertj:assertj-core:3.21.0

package edu.jab.math;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Problem20220123 {

    public static void main(String[] args) {

        Predicate<Integer> isPrime =
                number -> {
                    if (number == 2) {
                        return true;
                    } else if (number == 1 || number % 2 == 0) {
                        return false;
                    }
                    return LongStream.rangeClosed(2, number / 2).noneMatch(i -> number % i == 0);
                };

        Predicate<Integer> checkPrimeDigits =
                number -> {
                    var stringValue = String.valueOf(number);
                    var digitLen = stringValue.length();
                    var countPrimes =
                            Stream.of(stringValue)
                                    .flatMap(s -> s.chars().mapToObj(c -> (char) c))
                                    .map(String::valueOf)
                                    .map(Integer::valueOf)
                                    .filter(isPrime)
                                    .count();

                    return (digitLen == countPrimes) ? true : false;
                };

        var solution =
                IntStream.rangeClosed(1, 1000)
                        .boxed()
                        .filter(isPrime.and(checkPrimeDigits))
                        .count();

        System.out.println(solution);
        assertThat(solution).isEqualTo(23);
    }
}
