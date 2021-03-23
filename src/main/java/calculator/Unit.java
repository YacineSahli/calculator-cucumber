package calculator;

public enum Unit {
    //TODO Accept all symbols when a unit has multiple symbols
    //TODO Accept plurals symbols
    //TODO We should round number to a certain decimal when printing the result to avoid double precision error

    unknown_unit("U/N", "U/N", "U/N", 0),

    //TODO Add more length unit
    //Length
    km("length", "km", "kilometers", 1000),
    m("length", "m", "meters", 1),
    dm("length", "dm", "decimeters", 0.1),
    cm("length", "cm", "centimeters", 0.01),
    mm("length", "mm", "millimeters", 0.001),
    nm("length", "nm", "nanometers", 0.000000001),
    in("length", "in", "inches", 0.0254),
    mi("length", "mi", "miles", 1609.334),
    nmi("length", "nmi", "nauticalMiles", 1852),
    ft("length", "ft", "feet", 0.3048),
    yd("length", "yd", "yard", 0.9144),

    //Area
    m2("area", "m^2", "square meters", 1),
    km2("area", "km^2", "square kilometers", 1000000),
    //currency

    //Power

    //Energy

    //Pressure

    //Speed

    //Temperature
    c ("temperature","C","degrees Celcius", 1, 0),
    k ("temperature","K","Kelvins", 1, -273.15),
    f ("temperature","F","degrees Fahrenheit", 0.55555555555, -32),

    //Time
    ps("time", "ps", "picoseconds", 0.000000000001),
    ns("time","ns","nanoseconds",0.000000001),
    //Using u instead of the greek later mu for ease of use
    us("time", "us", "microseconds", 0.000001),
    ms("time", "ms", "milliseconds", 0.001),
    s("time", "s", "seconds", 1),
    min("time", "m", "minutes", 60),
    h("time", "h", "hours", 3600),
    day("time", "day", "days", 86400),

    //Volume
    m3("volume", "m^3", "cubic meters", 1),
    ft3("volume", "ft^3", "cubic foot", 0.02831685),
    in3("volume", "in^3", "cubic inch", 0.000016387064),
    cm3("volume", "cm^3", "cubic centimetre", 0.000001),
    l("volume", "l", "litre", 0.001),

    //Weight
    N("weight", "N", "newtons", 1),
    lbf("weight", "lbf", "pound-force", 4.448222),
    dyn("weight", "dyn", "dyne", 0.00001),
    pdl("weight", "pdl", "poundal", 0.1382550),

    //Mass
    oz("mass", "oz", "ounzes", 28.34952),
    lb("mass", "lb", "pounds", 453.5924),
    g("mass","g","grams", 1),
    mg("mass","mg","milligrams", 0.001),
    kg("mass", "kg", "kilograms", 1000),
    t("mass", "t", "tons", 1000000);

    public final String type, shortName, fullName;
    public final double value, offset;

    Unit(String stype, String sshortName, String sfullName, double dvalue, double doffset) {
        type = stype;
        shortName = sshortName;
        fullName = sfullName;
        value = dvalue;
        offset = doffset;

    }

    //Constructor without offset
    Unit(String stype, String sshortName, String sfullName, double dvalue) {
        type = stype;
        shortName = sshortName;
        fullName = sfullName;
        value = dvalue;
        offset = 0;

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
