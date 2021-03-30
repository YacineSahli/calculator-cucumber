package junit5tests;

import calculator.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEnergyConverter {
    private Calculator c = new Calculator();

    //TODO Needs more precision to be accurate, replace double with BigDecimal
    /*
    @Test
    public void testElectronvoltToJoule() {
        assertEquals(8.21197577481, c.convert("eV", "J", 512551235251.0),  0.001);
    }
    */
    @Test
    public void testJouleToHorsepowerHour() {
        assertEquals(0.242596856, c.convert("J", "hph", 651256),  0.0001);
    }
}
