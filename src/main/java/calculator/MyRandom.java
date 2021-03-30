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

    public CalculatorValue generate(Class<IntegerNumber> c, IntegerNumber bound){
        return new IntegerNumber(generator.nextInt(bound.getValue()+1));
    }

    public CalculatorValue generate(Class<RationalNumber> c, RationalNumber bound){
        int num, denum = 0;
        num = generator.nextInt();
        int bNum = bound.getNum();
        Integer threshold = null;
        if(bNum == 0){
            denum = -generator.nextInt();
        }else{
            threshold = num * bound.getDenum() / bound.getNum();
        }
        if (bNum > 0){
            denum = generator.ints(1, threshold, Integer.MAX_VALUE).toArray()[0];

        }else if(bNum < 0){
            denum = generator.nextInt(threshold);
        }
        return new RationalNumber(num, denum);
    }
}
