package edu.jab.math;

import org.matheclipse.core.eval.EvalUtilities;

public class Problem20220205 {

    public static void main(String[] args) {
        var util = new EvalUtilities(false, true);

        var equation =
            """
            Solve(
            {(x - 2) + (x-1)^2 + x^3 ==
            (x - 2 + x - 1 + x)^2 },
            {x})
            """;
        var solution = util.evaluate(equation);

        System.out.println(solution);
    }
}
