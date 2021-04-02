package junit5tests;

import calculator.IntegerNumber;
import calculator.NumberBuilder;
import calculator.RationalNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestNumberBuilder {

    private final int value1 = 7;
    private final int value2 = 42;


    @Test
    void builder() {
        NumberBuilder builder = NumberBuilder.builder();
        assertNotNull(builder);
        assertSame(builder, NumberBuilder.builder());
    }

    @Test
    void build() {
        NumberBuilder builder = NumberBuilder.builder();
        assertEquals(new IntegerNumber(value1), builder.build(value1, 1));
        assertEquals(new RationalNumber(value1, value2), builder.build(value1, value2));
    }
}