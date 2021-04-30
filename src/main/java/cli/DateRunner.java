package cli;

import calculator.Expression;
import calculator.antlr4.datetime_grammarLexer;
import calculator.antlr4.datetime_grammarParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class DateRunner extends Runner {

    public Expression parse(String data) {
        CharStream charStream = CharStreams.fromString(data);
        datetime_grammarLexer lex = new datetime_grammarLexer(charStream);
        CommonTokenStream st = new CommonTokenStream(lex);
        datetime_grammarParser parser = new datetime_grammarParser(st);

        lex.removeErrorListeners();
        lex.addErrorListener(lexerBaseErrorListener);

        parser.removeErrorListeners();
        parser.addErrorListener(baseErrorListener);

        DateVisitor visitor = new DateVisitor();
        return visitor.visit(parser.expression());
    }

    @Override
    public String getHelp() {
        return super.getHelp() + "--------------------------------------------- CONVERTOR HELP ------------------------------------------------\n\n" +
                "This mode allows you to do addition and substraction operations on dates and duration.\n" +
                "|:---------------------------------------------------------------------------------------:|\n" +
                "| PLUS            |     '+'      |           2020-12-11 10:10:10 + 2020-12-11 10:10:10    |\n" +
                "| MINUS           |     '-'      |           2020-12-11 10:10:10 - PT10H20M55S            |\n" +
                "|-----------------------------------------------------------------------------------------|\n\n"+

                "List of supported operations" +
                "| DATE + DURATION| DATE - DURATION  | DATE - DATE |\n\n" +
                "INFIX, PREFIX and POSTFIX notations are supported. Examples\n" +
                "| +(DATE, DURATION)         |        DATE + DURATION       |         (DATE, DURATION)+    |\n" +
                "The separator inside the parenthesis for the POSTFIX and PREFIX notation can either be a space or a comma\n" +
                "The supported date format are as follow.\n" +
                "z being a timezone like GMT, CET, GMT+8:30\n" +
                "a is the AM/PM hour otherwise the date is considered 24h format\n" +
                "|:----------------------------------------:|\n" +
                "| yyyy-MM-dd HH:mm:ss z                    |\n" +
                "| yyyy-MM-dd HH:mm z                       |\n" +
                "| yyyy-MM-dd HH z                          |\n" +
                "| yyyy-MM-dd z                             |\n" +
                "| yyyy-MM-dd HH:mm:ss a z                  |\n" +
                "| yyyy-MM-dd HH:mm a  z                    |\n" +
                "| yyyy-MM-dd HH a z                        |\n" +
                "| yyyy-MM-dd HH:mm                         |\n" +
                "| yyyy-MM-dd HH                            |\n" +
                "| yyyy-MM-dd                               |\n" +
                "| yyyy-MM-dd hh:mm:ss a                    |\n" +
                "| yyyy-MM-dd hh:mm a                       |\n" +
                "| yyyy-MM-dd hh a                          |\n" +
                "|------------------------------------------|\n\n"+

        "The supported duration format is ISO-8601 with entire seconds. Examples.\n\n" +
                "|:-------------------------------------------------:|\n" +
                "| 1 day                         | P1D               |\n" +
                "| 2 hours                       | PT2H              |\n" +
                "| 2 days, 3 hours and 4 minutes | P2DT3H4M          |\n" +
                "|---------------------------------------------------|\n\n";
    }
}
