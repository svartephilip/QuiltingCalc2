package com.magnus.oskar.quiltingcalc.calculations;

/**
 * Created by Magnus on 14.04.2017.
 */

public class Fractions {
    //Data field
    protected int numerator, denominator, whole;
    protected double decimal;


    //constructors
    public Fractions() {
        whole = 0;
        decimal = 0;
        numerator = 0;
        denominator = 1;
    }

    public Fractions(double number) {
        whole = (int)number;
        number -= whole;

        numerator = (int)Math.round(number*16);
        denominator = 16;

        int common = commonFactor(numerator, denominator);
        numerator /= common;
        denominator /= common;
        setDecimal();
    }

    public Fractions(int newNumerator, int newDenominator, int newWhole) {
        whole = newWhole + (newNumerator / newDenominator);
        denominator = newDenominator;
        numerator = newNumerator % newDenominator;
        setDecimal();
    }

    //setters
    public void setRational(double number) {
        whole = (int)number;
        number -= whole;

        numerator = (int)Math.round(number*16);
        denominator = 16;

        int common = commonFactor(numerator, denominator);
        numerator /= common;
        denominator /= common;

        setDecimal();
    }
    public void setRational(int newNumerator, int newDenominator, int newWhole) {
        whole = newWhole + ((int)(newNumerator / newDenominator));
        denominator = newDenominator;
        numerator = newNumerator % newDenominator;

        setDecimal();

        //to make sure the fraction is within my limit
        setRational(decimal);
    }
    public void setDecimal() {
        decimal = (double)whole + ((double)numerator / (double)denominator);
    }
    public void setDecimal(int newNumer, int newDenom, int newWhole) {
        decimal = (double)newWhole + ((double)newNumer / (double)newDenom);
        setRational(decimal);
    }
    public void setDecimal(double newDecimal) {
        decimal = newDecimal;
        setRational(decimal);
    }
    private void setDenom(int newDenom) {
        denominator = newDenom;
    }
    private void setNumer(int newNumer) {
        numerator = newNumer;
    }
    private void setWhole(int newWhole) {
        whole = newWhole;
    }

    //getters
    public int getDenom() {
        return denominator;
    }

    public int getNumer() {
        return numerator;
    }

    public int getWhole() {
        return whole;
    }

    public double getDecimal() {
        return decimal;
    }

    // public methods
    public Fractions add(double adder) {

        // converts the adder, then call the other add method.
        Fractions rationalAdder = new Fractions();
        rationalAdder.setRational(adder);

        return add(rationalAdder);
    }

    public Fractions add(Fractions adder) {
        numerator *= adder.getDenom();
        adder.setNumer(adder.getNumer() * denominator);

        denominator *= adder.getDenom();
        numerator += adder.getNumer();

        int common = commonFactor(numerator, denominator);
        denominator /= common;
        numerator /= common;
        whole += numerator / denominator;
        numerator %= denominator;

        Fractions returnValue = new Fractions();
        returnValue.setRational(numerator, denominator, whole);
        return returnValue;
    }

    public Fractions multiply(double doubleMultiplier) {

        // Convert to rational, then call the oter multiply method.
        Fractions rationalMultiplier = new Fractions();
        rationalMultiplier.setRational(doubleMultiplier);

        return multiply(rationalMultiplier);
    }

    public Fractions multiply(Fractions rationalMultiplier) {
        whole *= rationalMultiplier.getWhole();
        numerator *= rationalMultiplier.getNumer();
        denominator *= rationalMultiplier.getDenom();

        Fractions returnValue = new Fractions();
        returnValue.setRational(numerator, denominator, whole);

        return returnValue;

    }

    public int compare(Fractions compareTo) {
        if(whole > compareTo.getWhole()) {
            return 1;
        }
        else if(whole == compareTo.getWhole() && (numerator / denominator) > (compareTo.getNumer() / compareTo.getDenom())) {
            return 1;
        }
        else if(whole == compareTo.getWhole() && numerator == compareTo.getNumer() && denominator == compareTo.getDenom()){
            return 0;
        }
        else {
            return -1;
        }
    }


    public String toString() {
        return whole + " " + numerator + "/" + denominator;
    }

    // private methods
    private static int commonFactor(int num, int div) {
        if(div == 0) {
            return num;
        }
        return commonFactor(div, num % div);
    }
}