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


public class MyTime implements Expression {
    private ZonedDateTime date = null;
    private LocalTime time = null;
    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
    private ZoneId zoneId;

    public MyTime getValue() {
        return this;
    }

    public /*constructor*/ MyTime(String s) throws ParseException {
        date = parseDate(s);
        time = parseTime(s);
        if( date == null && time == null){
            throw new ParseException("Cannot parse input", 0);
        }
    }

    LocalTime parseTime(String s) throws ParseException {
        List<DateTimeFormatter> knownPatterns = new ArrayList<DateTimeFormatter>();
        knownPatterns.add(DateTimeFormatter.ofPattern("HH:mm:ss z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("HH:mm z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("HH z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("HH").withZone(ZoneOffset.UTC));

        for (DateTimeFormatter formatter : knownPatterns) {
            try {
                return LocalTime.parse(s, formatter);
            } catch(DateTimeParseException e){

            }
        }
        return null;
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
            } catch(DateTimeParseException e){

            }
        }
        return null;
    }

    public Duration getAsDuration(){
        long seconds;
        if (date == null) {
            seconds = time.getLong(ChronoField.INSTANT_SECONDS);
        }else{
            seconds = date.getLong(ChronoField.INSTANT_SECONDS);
        }
        Duration res = Duration.ofSeconds(seconds) ;
        return res;
    }
    @Override
    public void accept(Visitor v) throws EvaluatorException {

    public void formatDate(String s){
        DateTimeFormatter.ofPattern(s).format(date);
    }

    public ZoneId getZoneId(){
        return this.zoneId;
    }
    @Override
    public void accept(Visitor v) throws EvaluatorException {

    }

    public Integer countDepth() {
        return 0;
    }

    public Integer countOps() {
        return 0;
    }

    public Integer countNbs() {
        return 1;
    }

    @Override
    public String toString() {
        return date.format(fmt);
    }

    //Two MyTime expressions are equal if the ZonedDateTime they contain are equal
    @Override
    public boolean equals(Object o) {
        // No object should be equal to null (not including this check can result in an exception if a MyNumber is tested against null)
        if (o == null) return false;

        // If the object is compared to itself then return true
        if (o == this) {
            return true;
        }

        // If the object is of another type then return false
        if (!(o instanceof MyTime)) {
            return false;
        }
        return this.date.equals(((MyTime) o).date);
        // I used == above since the contained value is a primitive value
        // If it had been a Java object, .equals() would be needed
    }

    // The method hashCode() needs to be overridden if the equals method is overridden; otherwise there may be problems when you use your object in hashed collections such as HashMap, HashSet, LinkedHashSet
    @Override
    public int hashCode() {
        return date.getNano();
    }
}


