package cl.uchile.dcc.scrabble.dataTypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class scrabbleBinaryTest {
    private scrabbleBinary bin;
    private String binValue;
    private int seed;
    private Random rng;
    private int strSize;
    private final int N = 25;

    /**
     * The set up to be done before each test.
     * The values are generated at random so that the tests are not biased
     */
    @BeforeEach
    void setUp(){
        binValue = "";
        seed = new Random().nextInt();
        rng = new Random(seed);
        strSize = rng.nextInt(20) + 1;
        for (int i=0; i<strSize; i++){
            int n = rng.nextInt(2);
            binValue += Integer.toString(n);
        }
        bin = new scrabbleBinary(binValue);
    }

    /**
     * The different tests that check that two instances that have the same value
     * are considered equal and two with different values are considered different
     */
    @RepeatedTest(N)
    void BinaryTest(){
        var expectedBin = new scrabbleBinary(binValue);
        assertEquals(expectedBin, bin);
        assertEquals(expectedBin.hashCode(), bin.hashCode());
    }


    /**
     * Test for checking the toString() method
     */
    @RepeatedTest(N)
    void toStringTest(){
        var stringToString = bin.toString();
        assertEquals(bin.getValue(), stringToString);
    }

    /**
     * Test for checking the toScrabString() method
     */
    @RepeatedTest(N)
    void toScrabStringTest(){
        var scrabString = bin.toScrabString();
        assertEquals(new scrabbleString(bin.getValue()), scrabString);
    }

    /**
     * Test for checking the toScrabBool() method
     */
    @RepeatedTest(N)
    void toScrabBoolTest(){
        assert(bin.toScrabBool() == null);
    }

    /**
     * Test for checking the toScrabInt() method
     */
    @RepeatedTest(N)
    void toScrabIntTest(){
        String value = bin.getValue();
        if (value.charAt(0) == '1') {
            value = bin.twosComplement().getValue();
            value = "-" + value;
        }
        var expected = Integer.parseInt(value,2);
        var scrabInt = bin.toScrabInt();
        assertEquals(new scrabbleInt(expected), scrabInt);
    }

    /**
     * Test for checking the toScrabFloat() method
     */
    @RepeatedTest(N)
    void toScrabFloatTest(){
        String value = bin.getValue();
        if (value.charAt(0) == '1') {
            value = bin.twosComplement().getValue();
            value = "-" + value;
        }
        var expected = Integer.parseInt(value,2);
        var scrabFloat = bin.toScrabFloat();
        assertEquals(new scrabbleFloat(expected), scrabFloat);
    }

    /**
     * Test for checking the toScrabBinary() method
     */
    @RepeatedTest(N)
    void toScrabBinaryTest(){
        var expectedBin = new scrabbleBinary(binValue);
        var scrabBin = bin.toScrabBinary();
        assertEquals(expectedBin.toScrabBinary(), scrabBin);
    }

    @RepeatedTest(N)
    void negationTest(){
        String noBin = bin.getValue();
        noBin = noBin.replace('0', '2').replace('1','0').replace('2','1');
        assertEquals(new scrabbleBinary(noBin), bin.negation());
    }

    @RepeatedTest(N)
    void conjTest(){
        scrabbleBool T = new scrabbleBool(true);
        scrabbleBool F = new scrabbleBool(false);
        assertEquals(bin, bin.conj(T));
        assertEquals(new scrabbleBinary(bin.getValue().replace('1','0')), bin.conj(F));

        String value = "";
        for (int i=0; i<strSize; i++){
            int n = rng.nextInt(2);
            value += Integer.toString(n);
        }

        scrabbleBinary newBin = new scrabbleBinary(value);
        int x = newBin.toScrabInt().getValue();
        int y = bin.toScrabInt().getValue();
        scrabbleInt z = new scrabbleInt(x & y);
        assertEquals(z.toScrabBinary(), bin.conj(newBin));
    }


    @RepeatedTest(N)
    void disjTest(){
        scrabbleBool T = new scrabbleBool(true);
        scrabbleBool F = new scrabbleBool(false);
        assertEquals(bin, bin.disj(F));
        assertEquals(new scrabbleBinary(bin.getValue().replace('0','1')), bin.disj(T));

        String value = "";
        for (int i=0; i<strSize; i++){
            int n = rng.nextInt(2);
            value += Integer.toString(n);
        }

        scrabbleBinary newBin = new scrabbleBinary(value);
        int x = newBin.toScrabInt().getValue();
        int y = bin.toScrabInt().getValue();
        scrabbleInt z = new scrabbleInt(x | y);
        assertEquals(z.toScrabBinary(), bin.disj(newBin));
    }

    @RepeatedTest(N)
    void sumTest(){
        int nInt = rng.nextInt();
        String value = bin.getValue();
        if (value.charAt(0) == '1') {
            value = bin.twosComplement().getValue();
            value = "-" + value;
        }
        var binInt = Integer.parseInt(value,2);

        scrabbleInt n = new scrabbleInt(nInt);
        scrabbleInt nSuma = new scrabbleInt(nInt + binInt);
        assertEquals(nSuma.toScrabBinary(), bin.sum(n));


    }


}