package calculator;

import calculator.variables.CalculatorVariable;
import cli.Mode;
import cli.Runner;
import visitor.EvaluatorException;
import visitor.Printer;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Memory {
    private final Calculator calculator;
    private final Printer printer;
    private Expression[] expressions;
    private int pointer;
    private int load;
    private final HashMap<Expression, CalculatorVariable> expressValueBinding;

    public Memory(Calculator calculator, int size) {
        this.calculator = calculator;
        printer = new Printer(Notation.INFIX, ",");
        expressions = new Expression[size];
        pointer = -1;
        load = 0;
        expressValueBinding = new HashMap<>();
    }

    public void add(Expression express, CalculatorVariable value) {
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
            expressValueBinding.put(express, value);
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
    public void save(String filename){
        try {
            FileWriter fileWriter = new FileWriter(filename);
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
    public void load(String filename, Mode mode){
        try {

            File file = new File(filename);
            Scanner reader = new Scanner(file);
            reset();
            while (reader.hasNextLine()){
                String toParse = reader.nextLine();
                Runner runner = mode.parser();
                Expression expression = runner.parse(toParse);
                calculator.eval(expression);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void reset(){
        pointer = -1;
        load = 0;
        expressions = new Expression[expressions.length];
        expressValueBinding.clear();
    }
    public Expression[] getExpressions() {
        return expressions;
    }

    public String getValue(Expression e) {
        return expressValueBinding.get(e).toString();
    }
}
