package model;

import java.util.Observable;

/**
 * HSV Color Model
 * Keep and control Hue, Saturation and Value/Brightness
 *
 * @author Priscila Ribas da Costa (riba0007@algonquinlive.com)
 * @version 1.0
 */
public class HSVModel extends Observable {

    // Constants
    public static final Integer MAX_HUE = 359;
    public static final Integer MAX_SATURATION = 100;
    public static final Integer MAX_VALUE = 100;
    public static final Integer MIN_HUE = 0;
    public static final Integer MIN_SATURATION = 0;
    public static final Integer MIN_VALUE = 0;

    // variables
    private Integer hue;
    private Integer saturation;
    private Integer value;

    /**
     * Initialize the instance with black by default
     */
    public HSVModel() {
        this( MIN_HUE, MIN_SATURATION, MIN_VALUE );
    }

    /**
     * Initialize the instance with given hue, saturation and value
     *
     * @param hue
     * @param saturation
     * @param value
     */
    public HSVModel( Integer hue, Integer saturation, Integer value ) {
        super();
        setHSV(hue,saturation,value);
    }

    private void updateObservers(){
        this.setChanged();
        this.notifyObservers();
    }

    public void asBlack(){
        setHSV( MIN_HUE, MIN_SATURATION, MIN_VALUE );
    }
    public void asRed(){
        setHSV( MIN_HUE, MAX_SATURATION, MAX_VALUE );
    }
    public void asLime(){
        setHSV( 120, 76, 80 );
    }
    public void asBlue(){
        setHSV( 240, MAX_SATURATION, MAX_VALUE );
    }
    public void asYellow(){
        setHSV( 60, MAX_SATURATION, MAX_VALUE );
    }
    public void asCyan(){
        setHSV( 180, MAX_SATURATION, MAX_VALUE );
    }
    public void asMagenta(){
        setHSV( 300, MAX_SATURATION, MAX_VALUE );
    }
    public void asSilver(){
        setHSV( MIN_HUE, MIN_SATURATION, 75 );
    }
    public void asGray(){
        setHSV( MIN_HUE, MIN_SATURATION, 50 );
    }
    public void asMaroon(){
        setHSV( MIN_HUE, MAX_SATURATION, 50 );
    }
    public void asOlive(){
        setHSV( 60, MAX_SATURATION, 50 );
    }
    public void asGreen(){
        setHSV( 120, MAX_SATURATION, 50 );
    }
    public void asPurple(){
        setHSV( 300, MAX_SATURATION, 50 );
    }
    public void asTeal(){
        setHSV( 180, MAX_SATURATION, 50 );
    }
    public void asNavy(){
        setHSV( 240, MAX_SATURATION, 50 );
    }

    public Integer getHue() {
        return hue;
    }

    public void setHue(Integer hue) {
        if (hue <= this.MAX_HUE && hue >= this.MIN_HUE) {
            this.hue = hue;
        }
        this.updateObservers();
    }

    public Integer getSaturation() {
        return saturation;
    }

    public void setSaturation(Integer saturation) {
        if (saturation <= this.MAX_SATURATION && saturation >= this.MIN_SATURATION) {
            this.saturation = saturation;
        }
        this.updateObservers();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        if (value <= this.MAX_VALUE && value >= this.MIN_VALUE) {
            this.value = value;
        }
        this.updateObservers();
    }

    public void setHSV( Integer hue, Integer saturation, Integer value ) {
        setHue(hue);
        setSaturation(saturation);
        setValue(value);
    }
}
