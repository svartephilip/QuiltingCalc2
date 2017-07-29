package com.magnus.oskar.quiltingcalc.calculations;

import java.text.DecimalFormat;

/**
 * Created by mhauk on 10.04.2017.
 */

public class Conversion {
    // Data fields
    private double cm, meters, inch, feet, yard;
    private Fractions rationalInch, rationalFeet, rationalYard;

    // Constants
    private final double INCHTOCMRATIO=2.54;
    private final double FEETTOINCHRATIO=12; //one (1) feet = twelve (12) inches
    private final double FEETTOYARDRATIO=3; // one (1) yard = three (3) feet

    DecimalFormat f = new DecimalFormat("#0.##");

    // Constructor
    //no need for an constructor with arguments
    public Conversion(){
        cm=0;
        meters=0;
        inch=0;
        feet=0;
        yard=0;
        rationalInch = new Fractions();
        rationalFeet = new Fractions();
        rationalYard = new Fractions();
    }

    // Setters
    //set respective value, and convert the others
    //that way all values are always converted right
    public void setCm(double newCm){
        cm = newCm;
        meters = cm / 100;
        inch = cm / INCHTOCMRATIO;
        feet = inch / FEETTOINCHRATIO;
        yard = feet / FEETTOYARDRATIO;
        rational(inch, feet, yard);
    }
    public void setMeters(double newMeters){
        meters = newMeters;
        cm = meters * 100;
        inch = cm / INCHTOCMRATIO;
        feet = inch / FEETTOINCHRATIO;
        yard = feet / FEETTOYARDRATIO;
        rational(inch, feet, yard);
    }
    public void setInch(double newInch){
        inch = newInch;
        cm = inch * INCHTOCMRATIO;
        meters = cm / 100;
        feet = inch / FEETTOINCHRATIO;
        yard = feet / FEETTOYARDRATIO;
        rational(inch, feet, yard);
    }
    public void setFeet(double newFeet){
        feet = newFeet;
        inch = feet * FEETTOINCHRATIO;
        yard = feet / FEETTOYARDRATIO;
        cm = inch / INCHTOCMRATIO;
        meters = cm / 100;
        rational(inch, feet, yard);
    }
    public void setYard(double newYard){
        yard = newYard;
        feet = yard * FEETTOYARDRATIO;
        inch = feet * FEETTOINCHRATIO;
        cm = inch / INCHTOCMRATIO;
        meters = cm / 100;
        rational(inch, feet, yard);
    }
    public void setRationalInch(int numerator, int denominator, int whole) {
        rationalInch.setRational(numerator, denominator, whole);
        rationalInch.setDecimal(numerator, denominator, whole);
        inch = rationalInch.getDecimal();
        cm = inch * INCHTOCMRATIO;
        meters = cm / 100;
        feet = inch / FEETTOINCHRATIO;
        yard = feet / FEETTOYARDRATIO;

        Fractions f = new Fractions();
        f.setRational((int)(whole % FEETTOINCHRATIO), (int)FEETTOINCHRATIO, 0);
        whole /= FEETTOINCHRATIO;

        rationalFeet.setRational(numerator, denominator * (int)FEETTOINCHRATIO, whole);
        rationalFeet.addRational(f);

        f.setRational((int)(whole % FEETTOYARDRATIO), (int)FEETTOYARDRATIO, 0);
        whole /= FEETTOYARDRATIO;
        rationalYard.setRational(rationalFeet.getNumer(), rationalFeet.getDenom() * (int)FEETTOYARDRATIO, whole);
        rationalYard.addRational(f);
    }
    public void setRationalFeet(int numerator, int denominator, int whole) {
        rationalFeet.setDecimal(numerator, denominator, whole);
        rationalFeet.setRational(numerator, denominator, whole);
        feet = rationalFeet.getDecimal();
        inch = feet * FEETTOINCHRATIO;
        yard = feet / FEETTOYARDRATIO;
        cm = inch * INCHTOCMRATIO;
        meters = cm / 100;

        rationalInch.setRational(numerator * (int)FEETTOINCHRATIO, denominator, whole * (int)FEETTOINCHRATIO);

        Fractions f = new Fractions();
        f.setRational((int)(whole % FEETTOYARDRATIO), (int)FEETTOYARDRATIO, 0);
        whole /= FEETTOYARDRATIO;
        rationalYard.setRational(numerator, denominator * (int)FEETTOYARDRATIO, whole);
        rationalYard.addRational(f);
    }
    public void setRationalYard(int numerator, int denominator, int whole) {
        rationalYard.setDecimal(numerator, denominator, whole);
        rationalYard.setRational(numerator, denominator, whole);
        yard = rationalYard.getDecimal();
        feet = yard * FEETTOYARDRATIO;
        inch = feet * FEETTOINCHRATIO;
        cm = inch * INCHTOCMRATIO;
        meters = cm / 100;

        numerator *= FEETTOYARDRATIO;
        whole *= FEETTOYARDRATIO;
        rationalFeet.setRational(numerator, denominator, whole);

        numerator *= FEETTOINCHRATIO;
        whole *= FEETTOINCHRATIO;
        rationalInch.setRational(numerator, denominator, whole);


    }

    // Getters
    //return the respective number so they can be used
    public double getCm() {
        return cm;
    }
    public double getMeters() {
        return meters;
    }
    public double getInch() {
        return inch;
    }
    public double getFeet() {
        return feet;
    }
    public double getYard() {
        return yard;
    }
    public Fractions getRationalInch() {
        return rationalInch;
    }
    public Fractions getRationalFeet() {
        return rationalFeet;
    }
    public Fractions getRationalYard() {
        return rationalYard;
    }

    private void rational(double i, double f, double y) {
        rationalInch.setRational(i);
        rationalFeet.setRational(f);
        rationalYard.setRational(y);
    }
    public String toString() {
        return f.format(cm) + " cm\n" + f.format(meters) + " m\n" + f.format(inch) + " inch\n" +
                f.format(feet) + " feet\n" + f.format(yard) + " yard\n" + rationalInch.toString() + " rational inch\n" +
                rationalFeet.toString() + " rational feet\n" + rationalYard.toString() + " rational yard";
    }
}