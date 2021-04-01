package calculator;

public enum Currency {
    unknown_currency("UNKNOWN"),

    CAD("CAD"),
    HKD("HKD"),
    ISK("ISK"),
    PHP("PHP"),
    DKK("DKK"),
    HUF("HUF"),
    CZK("CZK"),
    AUD("AUD"),
    RON("RON"),
    SEK("SEK"),
    IDR("IDR"),
    INR("INR"),
    BRL("BRL"),
    RUB("RUB"),
    HRK("HRK"),
    JPY("JPY"),
    THB("THB"),
    CHF("CHF"),
    SGD("SGD"),
    PLN("PLN"),
    BGN("BGN"),
    TRY("TRY"),
    CNY("CNY"),
    NOK("NOK"),
    NZD("NZD"),
    ZAR("ZAR"),
    USD("USD"),
    MXN("MXN"),
    ILS("ILS"),
    GBP("GBP"),
    KRW("KRW"),
    MYR("MYR"),
    EUR("EUR");


    public final String ticker;

    Currency(String sticker) {
        ticker = sticker;
    }

    public static Currency stringToCurrency(String s) {
        for (Currency a : Currency.values())
            if (s.equalsIgnoreCase(a.ticker))
                return a;
        return Currency.unknown_currency;
    }
}
