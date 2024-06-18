package edu.jab.math;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Problem20220125 {

    public static void main(String[] args) {
        Predicate<Long> isOdd = number -> (number % 2 == 0) ? false : true;

        record Combination(Long x, Long y) {}

        Function<Integer, Stream<Combination>> crossProduct = number ->
            LongStream.rangeClosed(1, number)
                .mapToObj(x -> LongStream.rangeClosed(1, number).mapToObj(y -> new Combination(x, y)))
                .flatMap(x -> x);

        final var DiceFaces = 6;
        var combinationList = crossProduct.apply(DiceFaces).toList();
        var totalCombinations = combinationList.size();
        var oddCombinations = combinationList.stream().map(c -> c.x * c.y).filter(isOdd).count();
        var solution = new BigDecimal(oddCombinations).divide(new BigDecimal(totalCombinations));

        System.out.println(solution);
        assertThat(solution).isEqualTo(new BigDecimal("0.25"));
    }
}
