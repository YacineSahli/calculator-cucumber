package calculator;

import calculator.variables.CalculatorVariable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * Expression that can be evaluated in the calculator
 *
 * @author Arnaud.P
 */
public abstract class ComputableExpression {
    protected String symbol;
    protected String funcName; // the name of the function to apply

    public CalculatorVariable op(CalculatorVariable... args) throws InvocationTargetException, NoSuchMethodException {

        //retrieve the most accurate level. i.e. with the lowest accuracy value
        CalculatorVariable most_accurate_item = Arrays.stream(args)
                .max((o1, o2) -> o2.getAccuracyLevel() - o1.getAccuracyLevel())
                .get();

        Class targetClass = most_accurate_item.getClass(); // The class to which all arguments will be converted
        String convert_fun_name = "to" + targetClass.getName()
                .substring(targetClass.getName().lastIndexOf(".") + 1);

        // convert all args to the targetClass
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

            // apply the function funcName.
            fun = this.getClass().getMethod(funcName, Collections.nCopies(args.length, targetClass)
                    .toArray(new Class[args.length]));
            return (CalculatorVariable) fun.invoke(this, convertedArgs);
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
