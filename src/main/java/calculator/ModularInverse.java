package calculator;

import java.util.ArrayList;
import java.util.List;

public class ModularInverse extends Operation{

    public ModularInverse(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "%-1";
    }

    private int[] egcd(int a, int b){
        int[] result;
        if (a == 0) {
            result = new int[]{b, 0, 1};
            return result;
        }
        result = egcd(b%a, a);
        int y=result[1], x=result[2];
        result[1] = x-((int)b/a)*y;
        result[2] = y;
        return result;
    }

    public CalculatorValue apply(IntegerNumber a, IntegerNumber m){
        int[] egcd = egcd(a.getValue(), m.getValue());
        if(egcd[0] !=1){
            throw new ArithmeticException("modular inverse does not exist");
        }
        return new IntegerNumber(egcd[1]%m.getValue());
    }
}
