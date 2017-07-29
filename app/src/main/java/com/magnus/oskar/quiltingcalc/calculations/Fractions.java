package com.magnus.oskar.quiltingcalc.calculations;

/**
 * Created by mhauk on 14.04.2017.
 */

public class Fractions {
    //Data field
    protected int numerator, denominator, whole;
    protected double decimal;

    //constructors
    public void Fractions() {
        decimal = 0;
        numerator = 0;
        denominator = 0;
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
    }
    public void setRational(int newNumerator, int newDenominator, int newWhole) {
        whole = newWhole + ((int)(newNumerator / newDenominator));
        denominator = newDenominator;
        numerator = newNumerator % newDenominator;
    }
    public void setDecimal(int newNumer, int newDenom, int newWhole) {
        decimal = (double)newWhole + ((double)newNumer / (double)newDenom);
    }
    public void setDecimal(double newDecimal) {
        decimal = newDecimal;
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
    public void addRational(Fractions newRational) {
        numerator *= newRational.getDenom();
        newRational.setNumer(newRational.getNumer() * denominator);

        denominator *= newRational.getDenom();
        numerator += newRational.getNumer();

        int common = commonFactor(numerator, denominator);
        denominator /= common;
        numerator /= common;
        whole += numerator / denominator;
        numerator %= denominator;
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
