package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;

import java.util.ArrayList;

public class InfixPrinter extends Visitor {
    private String displayed;
    private String output = "";

    @Override
    public void visit(MyNumber n) {
        displayed = n.toString();
    }

    @Override
    public void visit(Operation o) {
        ArrayList<Integer> evaluatedArgs = new ArrayList<>();
        Expression a;
        //first loop to recursively evaluate each subexpression
        output += "( ";
        for (int i = 0; i < o.args.size() - 1; i++) {
            a = o.args.get(i);
            a.accept(this);
            output += displayed + " " + o.toString() + " ";
        }
        a = o.args.get(o.args.size() - 1);
        a.accept(this);
        output += displayed;
        displayed = "";
        output += " )";
    }

    public String getDisplayed() {
        return output;
    }
}
