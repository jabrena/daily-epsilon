/// usr/bin/env jbang "$0" "$@" ; exit $?
// DEPS org.assertj:assertj-core:3.21.0
// DEPS org.apache.commons:commons-math3:3.6.1

package edu.jab.math;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.math3.primes.Primes;

public class Problem20220123 {

    public static void main(String[] args) {

        Predicate<Integer> isPrime = number -> Primes.isPrime(number);

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
