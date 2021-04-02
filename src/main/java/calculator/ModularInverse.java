package calculator;

import java.util.List;

/**
 * Operation that can compute the modular multiplicative inverse.
 *
 * @author Arnaud.P
 */
public class ModularInverse extends Operation {

    public ModularInverse(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "%-1";
    }

    /**
     * @param a
     * @param b
     * @return An integer array containing the gcd(a, b) and the coefficients of Bézout's identity,
     * which are integers x and y such that ax+by= gcd(a,b).
     */
    private int[] egcd(int a, int b) {
        int[] result;
        if (a == 0) {
            result = new int[]{b, 0, 1};
            return result;
        }
        result = egcd(b % a, a);
        int y = result[1], x = result[2];
        result[1] = x - (b / a) * y;
        result[2] = y;
        return result;
    }

    public CalculatorValue op(IntegerNumber a, IntegerNumber m) {
        int[] egcd = egcd(a.getValue(), m.getValue());
        if (egcd[0] != 1) {
            //if a and m are not prime to each other, the inverse modulo does not exist.
            throw new ArithmeticException("modular inverse does not exist");
        }
        return new IntegerNumber(egcd[1] % m.getValue());
    }
}
