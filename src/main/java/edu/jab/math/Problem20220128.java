package edu.jab.math;

import org.matheclipse.core.eval.EvalUtilities;

public class Problem20220128 {

    public static void main(String[] args) {
        var util = new EvalUtilities(false, true);

        var equation = "Solve({(x/4)*(x/7)==x, x>0}, {x})";
        var solution = util.evaluate(equation);

        System.out.println(solution);
    }
}
