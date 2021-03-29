package visitor;

import calculator.CalculatorValue;
import calculator.Expression;
import calculator.Operation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Evaluator extends Visitor {

    private CalculatorValue computedValue;

    public CalculatorValue getResult() { return computedValue; }

    public void visit(CalculatorValue v){
        computedValue = v;
    }

    public void visit(Operation o) throws EvaluatorException {
        ArrayList<CalculatorValue> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for(Expression a:o.args) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
        //second loop to accummulate all the evaluated subresults
        CalculatorValue temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();
        CalculatorValue evaluated;
        for(int counter=1; counter<max; counter++) {
            try {
                //temp = o.op(temp, evaluatedArgs.get(counter));
                evaluated = evaluatedArgs.get(counter);
                if((temp.getAccuracyLevel()>=evaluated.getAccuracyLevel()) || (temp.canInteract() && evaluated.canInteract())){
                    temp = applyOp(evaluated,temp,o, true);
                }else if(temp.getAccuracyLevel()<evaluated.getAccuracyLevel()){
                    temp = applyOp(temp, evaluated,o, false);
                }else{
                    throw new EvaluatorException("cannot apply an operation between "+temp.toString()+" and "+
                            evaluated.toString(), o);
                }

            }catch (ArithmeticException e){
                // If a problem is encountered during the evaluation, throw a VisitorException
                throw new EvaluatorException("Impossible to evaluate Operation: "+e.getMessage(), o);
            }
        }
        // store the accumulated result
        computedValue = temp;
    }

    /**
     *
     * @param v1 Value downcasted
     * @param v2 Value converted to v1 Class
     * @param o Operation to apply
     * @return the result of the operation on v1 and v2
     */
    public CalculatorValue applyOp(CalculatorValue v1, CalculatorValue v2, Operation o, boolean inverted){
        Class[] arg_types = new Class[]{v1.getClass(), v1.getClass()};

        String[] v1CLassName = v1.getClass().getName().split("\\.");//get the subclass name of v1

        /* Generate the name of the convert method
         * WARNING: this method must be implemented in v2 !!
         */
        String convertFuncName = "to"+v1CLassName[v1CLassName.length-1];

        Method convertMethod = null;
        CalculatorValue result = null;
        try {
            // get the convert method
            convertMethod = v2.getClass().getMethod(convertFuncName);

            //apply the opeperation on v1 and v2 that have the same type
            //result = (CalculatorValue) o.getClass().getMethod("op", arg_types).invoke(o, v1, convertMethod.invoke(v2));
            Method op = o.getClass().getMethod("op", arg_types);
            if(inverted){
                result = (CalculatorValue) op.invoke(o, convertMethod.invoke(v2), v1);
            }else{
                result = (CalculatorValue) op.invoke(o, v1, convertMethod.invoke(v2));
            }
        } catch (NoSuchMethodException e) {//todo exception handling
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            //todo check what kind of exception is it.
            //for example, this exception is throw when the ArithmeticException is throw in divide
            e.printStackTrace();
        }
        return result;
    }

}
