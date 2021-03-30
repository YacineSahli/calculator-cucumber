package calculator;


public class NumberBuilder {

    private static NumberBuilder instance;

    public static NumberBuilder builder() {
        if (instance == null) {
            instance = new NumberBuilder();
        }
        return instance;
    }
    /*
    //todo implement for CLI

    private final ArrayList<Class> numberTypes;
    private NumberBuilder() {
        numberTypes = new ArrayList<>();
    }
    public void registerNumber(Class c){
        numberTypes.add(c);
    }

    public Number build(String strValue) throws NumberConstructionException{
        Pattern pattern;
        Matcher matcher;
        Constructor constructor;
        for(Class child: numberTypes) {
            try{
                pattern = (Pattern) child.getField("pattern").get(null);
                matcher = pattern.matcher(strValue);
                if (matcher.matches()) {
                    constructor = child.getConstructor(String.class);
                    return (Number) constructor.newInstance(strValue);
                }
            } catch (InvocationTargetException e) {
                //division by zero
            } catch (Exception e) {
                throw new NumberConstructionException(e);
            }
        }
        return null;
    }
 */

    public CalculatorValue build(int num, int denum) {
        if (denum == 1) {
            return new IntegerNumber(num);
        }
        return new RationalNumber(num, denum);
    }

}
