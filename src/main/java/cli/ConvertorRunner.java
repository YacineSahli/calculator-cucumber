package cli;

import calculator.Expression;

public class ConvertorRunner extends Runner {
    public Expression parse(String data) {
        return null;
    }

    @Override
    public String getHelp() {
        return super.getHelp() + "\n"  + loadHelpFile("ConvertorHelper.txt");
                /*"--------------------------------------------- CONVERTOR HELP ------------------------------------------------\n\n" +
                "This mode allows you to convert units.\n" +
                "You just have to write 3 parameters 'inputUnit' 'outputUnit' 'amount'.\n\n" +
                "Example:  'kg lb 100' will convert 100 kilograms to pounds\n" +
                "The following units are supported are available:\n\n" +
                "| Length |\n" +
                "|:--------:|\n" +
                "| km |\n" +
                "| m |\n" +
                "| dm |\n" +
                "| cm |\n" +
                "| mm |\n" +
                "| nm |\n" +
                "| in |\n" +
                "| mi |\n" +
                "| nmi |\n" +
                "| ft |\n" +
                "| yd |\n" +
                "---------\n" +

                "| Area |\n" +
                "|:--------:|\n" +
                "| m2 |\n" +
                "| km2 |\n" +
                "---------\n" +

                "| Power |\n" +
                "|:--------:|\n" +
                "| W |\n" +
                "| hp |\n" +
                "| TR |\n" +
                "---------\n" +

                "| Energy |\n" +
                "|:--------:|\n" +
                "| J |\n" +
                "| eV |\n" +
                "| ftlbf |\n" +
                "| hph |\n" +
                "---------\n" +

                "| Pressure |\n" +
                "|:--------:|\n" +
                "| Pa |\n" +
                "| bar |\n" +
                "| kPa |\n" +
                "| MPa |\n" +
                "| psi |\n" +
                "| inHg |\n" +
                "| mmHg |\n" +
                "| Torr |\n" +
                "---------\n" +

                "| Speed |\n" +
                "|:--------:|\n" +
                "| mps |\n" +
                "| kmph |\n" +
                "| mph |\n" +
                "| kts |\n" +
                "| ftps |\n" +
                "---------\n" +

                "| Temperature |\n" +
                "|:--------:|\n" +
                "| c |\n" +
                "| k |\n" +
                "| f |\n" +
                "---------\n" +

                "| Time |\n" +
                "|:--------:|\n" +
                "| ps |\n" +
                "| ns |\n" +
                "| us |\n" +
                "| ms |\n" +
                "| s |\n" +
                "| min |\n" +
                "| h |\n" +
                "| day |\n" +
                "---------\n" +

                "| Volume |\n" +
                "|:--------:|\n" +
                "| m3 |\n" +
                "| ft3 |\n" +
                "| in3 |\n" +
                "| cm3 |\n" +
                "| l |\n" +
                "---------\n" +

                "| Weight |\n" +
                "|:--------:|\n" +
                "| N |\n" +
                "| lbf |\n" +
                "| dyn |\n" +
                "| pdl |\n" +
                "---------\n" +

                "| Mass |\n" +
                "|:--------:|\n" +
                "| oz |\n" +
                "| lb |\n" +
                "| g |\n" +
                "| mg |\n" +
                "| kg |\n" +
                "| t |\n" +

                "----------------------------------------------------------------------------------------------------------\n";*/
    }
}

