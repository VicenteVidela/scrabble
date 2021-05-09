package cl.uchile.dcc.scrabble.dataTypes;

/**
 * The Scrabble Float class. It encapsulates a native Java double
 * that has the value of the instance
 */
public class Float extends AbstractDataType{
    private final double value;

    /**
     * The constructor of the class
     * @param x The value that will be assigned to the instance
     */
    public Float(double x){
        super(Double.toString(x));
        this.value = x;
    }
}
