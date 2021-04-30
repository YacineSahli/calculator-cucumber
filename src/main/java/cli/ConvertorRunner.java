package cli;

import calculator.Expression;

public class ConvertorRunner extends Runner {
    public Expression parse(String data) {
        return null;
    }

    @Override
    public String getHelp() {
        return super.getHelp() + "\n"  + loadHelpFile("ConvertorHelper.txt");
    }
}

