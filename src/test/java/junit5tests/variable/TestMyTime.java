package junit5tests.variable;

import calculator.variables.MyTime;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMyTime {
    private MyTime time;


    @Test
    public void testParseIsoDate() throws ParseException {
        time = new MyTime("2020-12-10 10:10:10");
        assertEquals("2020-12-10 10:10:10", time.toString());
    }

    @Test
    public void testParseIsoDate2() throws ParseException {
        time = new MyTime("2020-12-10");
        assertEquals("2020-12-10", time.toString());
    }

    @Test
    public void testParseIsoDate3() throws ParseException {
        time = new MyTime("2020-12-10 12");
        assertEquals("2020-12-10 12", time.toString());
    }

    @Test
    public void testParseIsoDate4() throws ParseException {
        time = new MyTime("2020-12-10 12:21");
        assertEquals("2020-12-10 12:21", time.toString());
    }

    @Test
    public void testParseIsoDate5() throws ParseException {
        time = new MyTime("2020-12-10 12:51:26");
        assertEquals("2020-12-10 12:51:26", time.toString());
    }


    @Test
    public void testParseIsoDatePST() throws ParseException {
        time = new MyTime("2020-12-10 10:10:10 PST");
        assertEquals("2020-12-10 10:10:10 PST", time.toString());
    }

    @Test
    public void testParseIsoDateCET() throws ParseException {
        time = new MyTime("2020-12-10 10:10:10 GMT+01:00");
        assertEquals("2020-12-10 10:10:10 GMT+01:00", time.toString());
    }

    @Test
    public void testParseFail() {
        assertThrows(ParseException.class, () -> time = new MyTime("3-2020-12-10 10:10:10"));
    }

    @Test
    public void testToHumanFormat() throws ParseException {
        time = new MyTime(Duration.ofSeconds(3912322));
        assertEquals("45d 6h 45m 22s", time.toHumanFormat());
    }

    @Test
    public void testToHumanFormatChosenTemporal() throws ParseException {
        time = new MyTime(Duration.ofSeconds(3912322));
        assertEquals("1086"+  DecimalFormatSymbols.getInstance().getDecimalSeparator() +"756 HOURS", time.toHumanFormat("HOURS"));
    }

    @Test
    public void testToHumanFormatChosenTemporalSubUnits1() throws ParseException {
        time = new MyTime(Duration.ofSeconds(3912322));
        assertEquals("1086HOURS 45MINUTES 22SECONDS", time.toHumanFormat("HOURS", false));
    }

    @Test
    public void testToHumanFormatChosenTemporalSubUnits2() throws ParseException {
        time = new MyTime(Duration.ofSeconds(3912322));
        assertEquals("65205Minutes 22Seconds", time.toHumanFormat("Minutes", false));
    }


}
