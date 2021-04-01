package calculator;

import java.util.Random;

public class MyRandom {

    private final Random generator;

    public MyRandom(long seed) {
        generator = new Random(seed);
    }

    public MyRandom() {
        generator = new Random();
    }

    public CalculatorValue nextInt() {
        return new IntegerNumber(generator.nextInt());
    }

    public CalculatorValue nextRational() {
        int num, denum = 0;
        num = generator.nextInt();
        denum = generator.nextInt();
        while (denum == 0) {
            denum = generator.nextInt();
        }
        return new RationalNumber(num, denum);
    }
}
