package cl.uchile.dcc.scrabble.dataTypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class scrabbleIntTest {
    private scrabbleInt n;
    private int nValue;
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
        nValue = rng.nextInt();
        n = new scrabbleInt(nValue);
    }

    /**
     * The different tests that check that two instances that have the same value
     * are considered equal, two with different values are considered different,
     * and also checks the toString method
     */
    @RepeatedTest(25)
    void IntTest(){
        var expectedN = new scrabbleInt(nValue);
        assertEquals(expectedN, n);
        int newN;
        do {
            newN = rng.nextInt();
        } while (newN == nValue);
        var differentN = new scrabbleInt(newN);
        assertNotEquals(differentN, n);
    }

    /**
     * Test for checking the toString() method
     */
    @RepeatedTest(25)
    void toStringTest(){
        var expectedN = new scrabbleInt(nValue);
        var intToString = n.toString();
        assert(intToString instanceof String);
        assertEquals(expectedN.toString(), intToString);
    }

    /**
     * Test for checking the toScrabString() method
     */
    @RepeatedTest(25)
    void toScrabStringTest(){
        var expectedInt = new scrabbleInt(nValue);
        var scrabString = n.toScrabString();
        assert(scrabString instanceof scrabbleString);
        assertEquals(expectedInt.toScrabString(), scrabString);
    }

    /**
     * Test for checking the toScrabFloat() method
     */
    @RepeatedTest(25)
    void toScrabFloatTest(){
        var expectedInt = new scrabbleInt(nValue);
        var scrabFloat = n.toScrabFloat();
        assert(scrabFloat instanceof scrabbleFloat);
        assertEquals(expectedInt.toScrabFloat(), scrabFloat);
    }

    /**
     * Test for checking the toScrabInt() method
     */
    @RepeatedTest(25)
    void toScrabIntTest(){
        var expectedInt = new scrabbleInt(nValue);
        var scrabInt = n.toScrabInt();
        assert(scrabInt instanceof scrabbleInt);
        assertEquals(expectedInt.toScrabInt(), scrabInt);
    }
}