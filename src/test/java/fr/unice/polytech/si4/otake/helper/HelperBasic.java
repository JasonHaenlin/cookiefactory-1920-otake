package fr.unice.polytech.si4.otake.helper;

/**
 * HelperBasic
 */
public class HelperBasic {

    public final static double increaseWithRatio(double value, double ratio) {
        return (value) * (1 + ratio);
    }

    public final static double decreaseWithRatio(double value, double ratio) {
        return (value) * (1 - ratio);
    }

}
