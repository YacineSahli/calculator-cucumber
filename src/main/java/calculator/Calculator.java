package calculator;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import visitor.Evaluator;
import visitor.Printer;
import visitor.EvaluatorException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import static calculator.Currency.stringToCurrency;
import static calculator.Unit.stringToUnit;

public class Calculator {

    /*
     For the moment the calculator only contains a print method and an eval method
     It would be useful to complete this with a read method, so that we would be able
     to implement a full REPL cycle (Read-Eval-Print loop) such as in Scheme, Python, R and other languages.
     To do so would require to implement a method with the following signature, converting an input string
     into an arithmetic expression:
     public Expression read(String s)
    */

    public void print(Expression e) {
        // use Printer to display de expression with the INFIX notation
        Printer p = new Printer(Notation.INFIX, ",");
        try {
            e.accept(p);
            System.out.println("The result of evaluating expression " + p.getResult());
        } catch (EvaluatorException evaluatorException) {
            System.out.println(evaluatorException.getMessage());
        }
        System.out.println("is: " + eval(e) + ".");
        System.out.println();
    }

    public void printExpressionDetails(Expression e) {
        print(e);
        System.out.print("It contains " + e.countDepth() + " levels of nested expressions, ");
        System.out.print(e.countOps() + " operations");
        System.out.println(" and " + e.countNbs() + " numbers.");
        System.out.println();
    }

    public Integer eval(Expression e){
        // create a new visitor to evaluate expressions
        Evaluator v = new Evaluator();
        // and ask the expression to accept this visitor to start the evaluation process
        try {
            e.accept(v);
        } catch (EvaluatorException evaluatorException) {
            System.out.println(evaluatorException.getMessage());
            return null;
        }
        // and return the result of the evaluation at the end of the process
        return v.getResult();
    }

    public Double convert(String inputUnit, String outputUnit, double inputAmount){
        if(stringToUnit(inputUnit) != Unit.unknown_unit && stringToUnit(outputUnit) != Unit.unknown_unit)
            return convertUnit(inputUnit, outputUnit, inputAmount);
        else if(stringToCurrency(inputUnit) != Currency.unknown_currency && stringToCurrency(outputUnit) != Currency.unknown_currency)
            return convertCurrency(inputUnit, outputUnit, inputAmount);
        return null;
    }

    public Double convertCurrency(String inputUnit, String outputUnit, double inputAmount){
        URL url = null;
        try {
            url = new URL("https://api.exchangeratesapi.io/latest?base=" + inputUnit + "&symbols=" + outputUnit);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        try {
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int responseCode = 0;
        try {
            responseCode = conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(responseCode != 200)
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        Scanner sc = null;
        try {
            sc = new Scanner(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = sc.nextLine();
        JsonElement jsonElement = new JsonParser().parse(json);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        double rate = Double.parseDouble(jsonObject.get("rates").getAsJsonObject().get(outputUnit).toString());
        return inputAmount * rate;
    }

    public Double convertUnit(String inputUnit, String outputUnit, double inputAmount){
        double result;
        Unit InputUnit = stringToUnit(inputUnit);
        Unit OutputUnit = stringToUnit(outputUnit);

        result = (inputAmount + InputUnit.offset) * InputUnit.value;
        result = (result / OutputUnit.value) - OutputUnit.offset;

        return result;
    }

    /*
     We could also have other methods, e.g. to verify whether an expression is syntactically correct
     public Boolean validate(Expression e)
     or to simplify some expression
     public Expression simplify(Expression e)
    */
}
