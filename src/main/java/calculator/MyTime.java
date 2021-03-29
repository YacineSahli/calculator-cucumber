package calculator;

import visitor.EvaluatorException;
import visitor.Visitor;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;


public class MyTime extends CalculatorValue {
    private ZonedDateTime date = null;
    private Duration time = null;
    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
    private ZoneId zoneId;



    public /*constructor*/ MyTime(String s) throws ParseException {
        super(s, 3, false);
        try {
            date = parseDate(s);
        }catch (ParseException e){}
        try {
            time = parseTime(s);
        } catch (ParseException e){}
        if (date == null && time == null) {
            throw new ParseException("Cannot parse input", 0);
        }
    }

    public /*constructor*/ MyTime(Duration d){
        super(d.toString(), 3, false);
        time = d;
    }


    public MyTime(ZonedDateTime dt) {
        super(dt.toString(), 3,false);
        date = dt;
    }


    public MyTime getValue() {
        return this;
    }

    public ZonedDateTime getZonedDateTime() {
        return date;
    }

    public Duration getLocalTime() {
        return time;
    }

    Duration parseTime(String s) throws ParseException {
        try{
            return Duration.parse(s);
        }catch (DateTimeParseException e){
            return null;
        }
    }

    ZonedDateTime parseDate(String s) throws ParseException {
        List<DateTimeFormatter> knownPatterns = new ArrayList<DateTimeFormatter>();
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh a z"));


        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh a").withZone(ZoneOffset.UTC));


        for (DateTimeFormatter formatter : knownPatterns) {
            try {
                return ZonedDateTime.parse(s, formatter);
            } catch (DateTimeParseException e) {

            }
        }
        return null;
    }

    public Duration getAsDuration() {
        long seconds;
        if (date == null) {
            return this.time;
        } else {
            seconds = date.toEpochSecond();
            Duration res = Duration.ofSeconds(seconds);
            return res;
        }
    }


    public void formatDate(String s) {
        DateTimeFormatter.ofPattern(s).format(date);
    }

    public ZoneId getZoneId() {
        return this.zoneId;
    }

    @Override
    public boolean specificEquals(Object o) {
        if (!(o instanceof MyTime)) {
            return false;
        }
        MyTime oTime = (MyTime) o;
        if (this.date == null) {
            if (oTime.date != null) {
                return false;
            }
            return this.time.equals(oTime.time);
        } else {
            if (oTime.time != null) {
                return false;
            }
            return this.date.equals(oTime.date);
        }
    }

    public MyTime toMyTime(){
        return this;
    }
}


