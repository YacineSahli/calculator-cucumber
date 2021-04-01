package calculator;

import visitor.EvaluatorException;
import visitor.Printer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Memory {
    private final Calculator calculator;
    private final Printer printer;
    private final Expression[] expressions;
    private int pointer;
    private int load;
    private final HashMap<Expression, CalculatorValue> expressValueBinding;

    public Memory(Calculator calculator, int size) {
        this.calculator = calculator;
        printer = new Printer(Notation.INFIX, ",");
        expressions = new Expression[size];
        pointer = -1;
        load = 0;
        expressValueBinding = new HashMap<>();
    }

    public void add(Expression express) {
        if (pointer + 1 < expressions.length) {
            pointer++;
            if (expressions[pointer] != null) {
                load = pointer + 1;
                int k = load;
                while (expressions[k] != null && k < expressions.length) {
                    expressValueBinding.remove(expressions[k]);
                    expressions[k] = null;
                    k++;
                }
            } else {
                load++;
            }
            expressions[pointer] = express;
            expressValueBinding.put(express, calculator.eval(express));
        } else {
            System.out.println("MEMORY FULL !");
        }

    }

    public void displayLog() {
        for (Expression e :
                expressions) {
            if (e == null) {
                break;
            } else {
                try {
                    e.accept(printer);
                    System.out.println(printer.getResult());
                } catch (EvaluatorException evaluatorException) {
                    evaluatorException.printStackTrace();
                }
            }

        }
    }

    public Expression undo() {
        if (load == 0) {
            return null;
        } else if (pointer - 1 >= 0) {
            pointer--;
            return expressions[pointer];
        } else {
            return expressions[pointer];
        }
    }

    public Expression redo() {
        if (pointer + 1 < load) {
            pointer++;
        }
        return expressions[pointer];
    }

    public void history() {
        try {
            FileWriter fileWriter = new FileWriter("history.txt");
            for (Expression e : expressions) {
                if (e == null) {
                    break;
                } else {
                    e.accept(printer);
                    fileWriter.write(printer.getResult() + '\n');
                }
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (EvaluatorException e) {
            e.printStackTrace();
        }
    }

    public int getPointer() {
        return pointer;
    }

    public int getLoad() {
        return load;
    }

    public Expression[] getExpressions() {
        return expressions;
    }
}
