package calculator;

import java.util.Random;

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

    public CalculatorValue nextInt(IntegerNumber bound){
        return new IntegerNumber(generator.nextInt(bound.getValue()));
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

    public Long getSeed() {
        return seed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyRandom myRandom = (MyRandom) o;

        return seed == myRandom.getSeed();
    }

    @Override
    public int hashCode() {
        return generator.hashCode();
    }
}
