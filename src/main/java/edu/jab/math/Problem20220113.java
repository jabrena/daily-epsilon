package edu.jab.math;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.math3.util.FastMath;

public class Problem20220113 {

    public static void main(String[] args) {
        // https://en.m.wikipedia.org/wiki/Binary_logarithm
        var result = FastMath.log(2, 5000);
        var solution = Math.ceil(result);

        System.out.println(solution);
        assertThat(solution).isEqualTo(13);
    }
}
