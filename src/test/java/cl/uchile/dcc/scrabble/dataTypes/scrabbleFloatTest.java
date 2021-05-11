package cl.uchile.dcc.scrabble.dataTypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class scrabbleFloatTest {
    private scrabbleFloat x;
    private double xValue;
    private int seed;
    private Random rng;

    /**
     * The set up to be done before each test.
     * The values are generated at random so that the tests are not biased
     */
    @BeforeEach
    void setUp(){
        seed = new Random().nextInt();
        rng = new Random(seed);
        xValue = rng.nextDouble();
        x = new scrabbleFloat(xValue);
    }

    /**
     * The different tests that check that two instances that have the same value
     * are considered equal, two with different values are considered different,
     * and also checks the toString method
     */
    @RepeatedTest(25)
    void floatTest(){
        var expectedX = new scrabbleFloat(xValue);
        assertEquals(expectedX, x);
        double newX;
        do {
            newX = rng.nextDouble();
        } while (newX == xValue);
        var differentX = new scrabbleFloat(newX);
        assertNotEquals(differentX, x);
    }

    /**
     * Test for checking the toString() method
     */
    @RepeatedTest(25)
    void toStringTest(){
        var expectedX = new scrabbleFloat(xValue);
        var floatToString = x.toString();
        assert(floatToString instanceof String);
        assertEquals(expectedX.toString(), floatToString);
    }

    /**
     * Test for checking the toScrabString() method
     */
    @RepeatedTest(25)
    void toScrabStringTest(){
        var expectedFloat = new scrabbleFloat(xValue);
        var scrabString = x.toScrabString();
        assert(scrabString instanceof scrabbleString);
        assertEquals(expectedFloat.toScrabString(), scrabString);
    }

    /**
     * Test for checking the toScrabFloat() method
     */
    @RepeatedTest(25)
    void toScrabFloatTest(){
        var expectedFloat = new scrabbleFloat(xValue);
        var scrabFloat = x.toScrabFloat();
        assert(scrabFloat instanceof scrabbleFloat);
        assertEquals(expectedFloat.toScrabFloat(), scrabFloat);
    }
}