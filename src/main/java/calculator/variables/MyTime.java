package calculator.variables;

import java.text.ParseException;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;


public class MyTime extends CalculatorVariable {
    private final static int ACCURACY = 1;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
    private ZonedDateTime date = null;
    private Duration time = null;
    private ZoneId zoneId;


    public /*constructor*/ MyTime(String s) throws ParseException {
        super(s, 3, false);
        date = parseDate(s);
        time = parseTime(s);
        if (date == null && time == null) {
            throw new ParseException("Cannot parse input", 0);
        }
    }

    public /*constructor*/ MyTime(Duration d) {
        super(d.toString(), 3, false);
        time = d;
    }


    public MyTime(ZonedDateTime dt) {
        super(dt.toString(), 3, false);
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

    private Duration parseTime(String s) {
        try {
            return Duration.parse(s);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @SuppressWarnings("CatchMayIgnoreException")
    private ZonedDateTime parseDate(String s) {
        List<DateTimeFormatter> knownPatterns = new ArrayList<>();
        knownPatterns.add(new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd[ HH:mm:ss] z")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter().withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a z"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh a z"));

        knownPatterns.add(new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd[ HH:mm:ss]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter().withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm O"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH O"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd O"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a O"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a O"));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh a O"));

        knownPatterns.add(new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd[ HH:mm:ss]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter().withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a").withZone(ZoneOffset.UTC));
        knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd hh a").withZone(ZoneOffset.UTC));


        knownPatterns.add(new DateTimeFormatterBuilder().appendPattern("[yyyy-MM-dd ]HH:mm:ss z")
                .parseDefaulting(ChronoField.YEAR_OF_ERA, ZonedDateTime.now().getYear())
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, ZonedDateTime.now().getMonthValue())
                .parseDefaulting(ChronoField.DAY_OF_MONTH, ZonedDateTime.now().getDayOfMonth())
                .toFormatter().withZone(ZoneOffset.UTC));

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
            return Duration.ofSeconds(seconds);
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

    public String toHumanFormat() {
        Duration d = this.getAsDuration();
        return String.format("%sd %sh %sm %ss",
                d.toDaysPart(),
                d.toHoursPart(),
                d.toMinutesPart(),
                d.toSecondsPart());
    }

    public String toHumanFormat(String outputUnit) {
        Duration d = this.getAsDuration();
        double res;
        switch (outputUnit) {
            case "DAYS":
            case "Days":
            case "days":
            case "d":
                res = (double) d.toSeconds() / 86400;
                break;
            case "HOURS":
            case "Hours":
            case "hours":
            case "h":
                res = (double) d.toSeconds() / 3600;
                break;
            case "MINUTES":
            case "Minutes":
            case "minutes":
            case "m":
                res = (double) d.toSeconds() / 60;
                break;
            case "SECONDS":
            case "Seconds":
            case "seconds":
            case "s":
                res = (double) d.toSeconds();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + outputUnit);
        }
        return String.format("%.3f %s", res, outputUnit);
    }

    public String toHumanFormat(String outputUnit, boolean fractional) {
        BiFunction<String, Duration, String> formatDays = (format, d) -> String.format(format,
                d.toDaysPart(),
                d.toHoursPart(),
                d.toMinutesPart(),
                d.toSecondsPart());
        BiFunction<String, Duration, String> formatHours = (format, d) -> String.format(format,
                d.toDaysPart() * 24 + d.toHoursPart(),
                d.toMinutesPart(),
                d.toSecondsPart());
        BiFunction<String, Duration, String> formatMinutes = (format, d) -> String.format(format,
                (d.toDaysPart() * 24 + d.toHoursPart()) * 60 + d.toMinutesPart(),
                d.toSecondsPart());
        BiFunction<String, Duration, String> formatSeconds = (format, d) -> String.format(format,
                d.toSeconds());


        if (fractional) return toHumanFormat(outputUnit);
        Duration d = this.getAsDuration();
        switch (outputUnit) {
            case "DAYS":
                return formatDays.apply("%sDAYS %sHOURS %sMINUTES %sSECONDS", d);
            case "Days":
                return formatDays.apply("%sDays %sHours %sMinutes %sSeconds", d);
            case "days":
                return formatDays.apply("%sdays %shours %sminutes %sseconds", d);
            case "d":
                return formatDays.apply("%sd %sh %sm %ss", d);
            case "HOURS":
                return formatHours.apply("%sHOURS %sMINUTES %sSECONDS", d);
            case "Hours":
                return formatHours.apply("%sHours %sMinutes %sSeconds", d);
            case "hours":
                return formatHours.apply("%shours %sminutes %sseconds", d);
            case "h":
                return formatHours.apply("%sh %sm %ss", d);
            case "MINUTES":
                return formatMinutes.apply("%sMINUTES %sSECONDS", d);
            case "Minutes":
                return formatMinutes.apply("%sMinutes %sSeconds", d);
            case "minutes":
                return formatMinutes.apply("%sminutes %sseconds", d);
            case "m":
                return formatMinutes.apply("%sm %ss", d);
            case "SECONDS":
                return formatSeconds.apply("%SECONDS", d);
            case "Seconds":
                return formatSeconds.apply("%Seconds", d);
            case "seconds":
                return formatSeconds.apply("%seconds", d);
            case "s":
                return formatSeconds.apply("%s", d);
            default:
                throw new IllegalStateException("Unexpected value: " + outputUnit);
        }
    }

    public MyTime toMyTime() {
        return this;
    }
}


