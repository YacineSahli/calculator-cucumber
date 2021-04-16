package cli;

import calculator.Expression;

public interface Parser {

    Expression parse(String data);
}
