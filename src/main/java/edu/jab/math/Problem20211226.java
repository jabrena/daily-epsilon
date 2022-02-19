/// usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.assertj:assertj-core:3.21.0
//DEPS org.apache.commons:commons-math3:3.6.1

package edu.jab.math;

import java.math.BigDecimal;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.math3.primes.Primes;

public class Problem20211226 {

    public static void main(String[] args) {
        Predicate<Integer> isPrime = number -> Primes.isPrime(number);

        Predicate<Integer> isOdd = number -> (number % 2 == 0) ? false : true;

        var result = Stream
            .iterate(1, i -> i + 1) // Infinite Stream
            .skip(1) // 1 is not a prime number
            .filter(isPrime)
            .filter(isOdd)
            .limit(4)
            .peek(System.out::println)
            .map(BigDecimal::valueOf)
            .reduce(BigDecimal.ZERO, BigDecimal::add); // Sum

        System.out.print("Result: " + result);
    }
}
