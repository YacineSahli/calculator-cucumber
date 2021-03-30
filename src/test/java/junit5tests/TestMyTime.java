package junit5tests;

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMyTime {
    private MyTime time;


    @Test
    public void testParseIsoDate() throws ParseException {
        time = new MyTime("2020-12-10 10:10:10");
        assertEquals("2020-12-10 10:10:10",time.toString());
    }

    @Test
    public void testParseIsoDatePST() throws ParseException {
        time = new MyTime("2020-12-10 10:10:10 PST");
        time.formatDate("yyyy-MM-dd HH:mm:ss z");
        assertEquals("2020-12-10 10:10:10 PST", time.toString());
    }

    @Test
    public void testParseFail() {
        assertThrows(ParseException.class, () -> time = new MyTime("3-2020-12-10 10:10:10"));
    }

}
