package calculator;

import visitor.Visitor;

public abstract class CalculatorValue implements Expression {

    private String strValue;
    protected int accuracyLevel;// ex: 1 is more accurate than 2
    protected boolean canInteract;// true if we can apply an operation with other types

    protected CalculatorValue(String strValue, int accuracy, boolean canInteract) {
        this.strValue = strValue;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Integer countDepth() {
        return 0;
    }

    public Integer countOps() {
        return 0;
    }

    public Integer countNbs() {
        return 1;
    }

    @Override
    public String toString() {
        return this.strValue;
    }

    public int getAccuracyLevel() {
        return accuracyLevel;
    }

    public boolean canInteract() {
        return canInteract;
    }

    public abstract boolean specificEquals(Object o);

    //Two MyNumber expressions are equal if the values they contain are equal
    @Override
    public boolean equals(Object o) {
        // No object should be equal to null (not including this check can result in an exception if a MyNumber is tested against null)
        if (o == null) return false;

        // If the object is compared to itself then return true
        if (o == this) {
            return true;
        }

        // If the object is of another type then return false
        if (!(o instanceof CalculatorValue)) {
            return false;
        }
        return specificEquals(o);
        // I used == above since the contained value is a primitive value
        // If it had been a Java object, .equals() would be needed
    }

    // The method hashCode() needs to be overridden if the equals method is overridden; otherwise there may be problems when you use your object in hashed collections such as HashMap, HashSet, LinkedHashSet
    @Override
    public int hashCode() {
        return strValue.hashCode();
    }
}
