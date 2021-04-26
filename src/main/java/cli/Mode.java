package cli;

public enum Mode {

    CALCULATOR(new ArithmeticParser()),
    BOOLEAN(new BooleanParser()), //todo
    CONVERTOR(null), //todo
    DATE(new DateParser()),
    HELP(null);

    private final Parser parser;

    Mode(Parser parser){
        this.parser=parser;
    }

    public Parser parser(){
        return this.parser;
    }
}
