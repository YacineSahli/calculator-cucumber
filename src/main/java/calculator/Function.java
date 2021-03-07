package calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO EXPERIMENTAL
 * TO complete
 */
public abstract class Function implements Expression{

    public List<Expression> args;

    public Function(List<Expression> elist) throws IllegalConstruction {
        if (elist == null) {
            throw new IllegalConstruction(); }
        else {
            args = new ArrayList<>(elist);
        }
    }

    public List<Expression> getArgs() {
        return args;
    }

    abstract public Object apply(Object o);

}
