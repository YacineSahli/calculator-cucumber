package calculator;

public enum Unit {
    //TODO Accept all symbols when a unit has multiple symbols

    unknown_unit("U/N", "U/N", "U/N", 0, 0),

    //TODO Add more length unit
    //Length
    km("length", "km", "kilometers", 1000, 0),
    m("length", "m", "meters", 1, 0),
    dm("length", "dm", "decimeters", 0.1, 0),
    cm("length", "cm", "centimeters", 0.01, 0),
    mm("length", "mm", "millimeters", 0.001, 0),
    nm("length", "nm", "nanometers", 0.000000001, 0),
    in("length", "in", "inches", 0.0254, 0),
    mi("length", "mi", "miles", 1609.334, 0),
    nmi("length", "nmi", "nauticalMiles", 1852, 0),
    ft("length", "ft", "feet", 0.3048, 0),
    yd("length", "yd", "yard", 0.9144, 0),

    //Area
    m2("area", "m^2", "square meters", 1, 0),
    km2("area", "km^2", "square kilometers", 1000000,0),
    //currency

    //Power

    //Energy

    //Pressure

    //Speed

    //Temperature

    //Time

    //Volume

    //weight

    //Masses
    g("mass","g","grams", 1, 0),
    kg("mass", "kg", "kilograms", 1000, 0),
    t("mass", "t", "tons", 1000000,0);


    public final String type, shortName, fullName;
    public final double value, offset;

    Unit(String stype, String sshortName, String sfullName, double dvalue, double doffset) {
        type = stype;
        shortName = sshortName;
        fullName = sfullName;
        value = dvalue;
        offset = doffset;

    }

    //TODO check type of unit in case 2 different type have the same shortname for example nm for nautical miles and
    // nanometer or throw ambiguous conversion error and ask the user to be more precise
    public static Unit stringToUnit(String s) {
        for (Unit a : Unit.values())
            if (s.equalsIgnoreCase(a.fullName) || s.equals(a.shortName))
                return a;
        return Unit.unknown_unit;
    }
}
