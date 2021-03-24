package calculator;

import visitor.Visitor;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class MyTime implements Expression {
    private final ZonedDateTime date;
    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

    public Integer getValue() {
        return date.getNano();
    }

    public /*constructor*/ MyTime(String s) throws ParseException {
        date = parseDate(s);
    }

    ZonedDateTime parseDate(String s) throws ParseException {
        List<DateTimeFormatter> knownPatterns = new ArrayList<DateTimeFormatter>();
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC));
        for (DateTimeFormatter formatter : knownPatterns) {
            try {
                return ZonedDateTime.parse(s, formatter);
            } catch(DateTimeParseException e){

            }
        }
        throw new ParseException("Cannot parse input", 0);
    }

    public void formatDate(String s){
        DateTimeFormatter.ofPattern(s).format(date);
    }

    public void accept(Visitor v) {
        v.visit(this);
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

    //Two MyNumber expressions are equal if the values they contain are equal
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
        return this.date == ((MyTime) o).date;
        // I used == above since the contained value is a primitive value
        // If it had been a Java object, .equals() would be needed
    }

    // The method hashCode() needs to be overridden if the equals method is overridden; otherwise there may be problems when you use your object in hashed collections such as HashMap, HashSet, LinkedHashSet
    @Override
    public int hashCode() {
        return date.getNano();
    }
}


