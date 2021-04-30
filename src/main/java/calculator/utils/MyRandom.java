package calculator.utils;

import calculator.variables.CalculatorVariable;
import calculator.variables.IntegerNumber;
import calculator.variables.RationalNumber;

import java.util.Objects;
import java.util.Random;

/**
 * A pseudorandom generator.
 *
 * @author Arnaud.P
 */
public class MyRandom {

    private final Random generator;
    private Long seed;

    public MyRandom(long seed) {
        generator = new Random(seed);
        this.seed = seed;
    }

    public MyRandom() {
        generator = new Random();
    }

    public CalculatorVariable nextInt(IntegerNumber bound) {
        return new IntegerNumber(generator.nextInt(bound.getValue()));
    }

    public CalculatorVariable nextInt() {
        return new IntegerNumber(generator.nextInt());
    }

    public CalculatorVariable nextRational() {
        int num, denum;
        num = generator.nextInt();
        denum = generator.nextInt();
        while (denum == 0) {
            denum = generator.nextInt();
        }
        return new RationalNumber(num, denum);
    }

    public Long getSeed() {
        return seed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyRandom myRandom = (MyRandom) o;

        return Objects.equals(seed, myRandom.getSeed());
    }

    @Override
    public int hashCode() {
        return generator.hashCode();
    }
}
