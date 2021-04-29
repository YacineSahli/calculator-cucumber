package cli;

public enum Mode {

    CALCULATOR(new ArithmeticRunner()),
    BOOLEAN(new BooleanRunner()), //todo
    CONVERTOR(new ConvertorRunner()), //todo
    DATE(new DateRunner());

    private final Runner runner;

    Mode(Runner runner){
        this.runner = runner;
    }

    public Runner parser(){
        return this.runner;
    }
}
