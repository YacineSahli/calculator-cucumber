package calculator.utils.convertor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.Scanner;

import static calculator.utils.convertor.Currency.stringToCurrency;
import static calculator.utils.convertor.Unit.stringToUnit;

public class Convertor {
    public Double convert(String input) {
        String[] inputList = input.split(" ");
        if (inputList.length != 3)
            throw new IllegalStateException("The format should be 'inputUnit outputUnit value;");
        return convert(inputList[0], inputList[1], Double.parseDouble(inputList[2]));
    }

    public Double convert(String inputUnit, String outputUnit, double inputAmount) {
        if (stringToUnit(inputUnit) != Unit.unknown_unit && stringToUnit(outputUnit) != Unit.unknown_unit)
            return convertUnit(inputUnit, outputUnit, inputAmount);
        else if (stringToCurrency(inputUnit) != Currency.unknown_currency && stringToCurrency(outputUnit) != Currency.unknown_currency)
            return convertCurrency(inputUnit, outputUnit, inputAmount);
        throw new IllegalStateException();
    }

    public Double convertCurrency(String inputUnit, String outputUnit, double inputAmount) {
        URL url;
        Scanner sc = null;
        try {
            url = new URL("https://api.exchangerate.host/latest&base=" + inputUnit.toUpperCase() + "&symbols=" + outputUnit.toUpperCase());
            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode;
            responseCode = conn.getResponseCode();
            if (responseCode != 200)
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            sc = new Scanner(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String json = sc.nextLine();
        double rate = getRateFromJson(json, outputUnit);
        return inputAmount * rate;
    }

    public double getRateFromJson(String json, String outputUnit) {
        JsonElement jsonElement = new JsonParser().parse(json);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement res = jsonObject.get("rates").getAsJsonObject().get(outputUnit);
        if(res == null)
            throw new IllegalStateException("Could not fetch data. Is the currency available ?");
        return Double.parseDouble(res.toString());
    }

    public Double convertUnit(String inputUnit, String outputUnit, double inputAmount) {
        double result;
        Unit InputUnit = stringToUnit(inputUnit);
        Unit OutputUnit = stringToUnit(outputUnit);
        if (!Objects.equals(InputUnit.type, OutputUnit.type))
            throw new InvalidParameterException("Input and output unit types does not match !");
        result = (inputAmount + InputUnit.offset) * InputUnit.value;
        result = (result / OutputUnit.value) - OutputUnit.offset;

        return result;
    }
}
