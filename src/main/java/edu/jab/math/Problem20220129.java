/// usr/bin/env jbang "$0" "$@" ; exit $?
// DEPS org.assertj:assertj-core:3.21.0

package edu.jab.math;

import org.matheclipse.core.eval.EvalUtilities;

public class Problem20220129 {

    public static void main(String[] args) {
        var util = new EvalUtilities(false, true);

        var equation =
            """
                Solve(
                {x^2 + y^2 - x - y == 814,
                (x*y) - x - y == 27 },
                {x,y})
                """;
        var solution = util.evaluate(equation);

        System.out.println(solution);
    }
}
