package calculator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public abstract class ComputableExpression {
    protected String symbol;
    protected String funcName;

    public CalculatorValue op(CalculatorValue... args) throws InvocationTargetException, NoSuchMethodException {
        CalculatorValue most_accurate_item = Arrays.stream(args).max((o1, o2) -> o2.accuracyLevel - o1.accuracyLevel).get();

        Class targetClass = most_accurate_item.getClass();
        String convert_fun_name = "to" + targetClass.getName().substring(targetClass.getName().lastIndexOf(".") + 1);

        final Exception[] exception = new Exception[1];
        try {
            Stream<Object> streamConvertedArgs = Arrays.stream(args).map(i -> {
                try {
                    return i.getClass().getMethod(convert_fun_name).invoke(i);
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    exception[0] = e;
                }
                return null;
            });
            if (exception[0] != null)
                throw exception[0];

            Object[] convertedArgs = streamConvertedArgs.toArray();

            Method fun = null;

            fun = this.getClass().getMethod(funcName, Collections.nCopies(args.length, targetClass).toArray(new Class[args.length]));
            return (CalculatorValue) fun.invoke(this, convertedArgs);
        } catch (InvocationTargetException e) {
            throw e;
        } catch (NoSuchMethodException e) {
            throw e;
        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComputableExpression that = (ComputableExpression) o;

        return funcName.equals(that.funcName);
    }

    @Override
    public int hashCode() {
        return funcName.hashCode();
    }
}
