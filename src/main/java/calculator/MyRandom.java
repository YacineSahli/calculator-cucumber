package calculator;

import java.util.Random;
import java.util.stream.IntStream;

public class MyRandom {

    private Random generator;

    public MyRandom(long seed){
        generator = new Random(seed);
    }

    public MyRandom(){
        generator = new Random();
    }

    public CalculatorValue nextInt(){
        return new IntegerNumber(generator.nextInt());
    }

    public CalculatorValue nextRational(){
        int num, denum = 0;
        num = generator.nextInt();
        denum = generator.nextInt();
        while(denum ==0){
            denum = generator.nextInt();
        }
        return new RationalNumber(num, denum);
    }
}
