package com.magnus.oskar.quiltingcalc.calculations;

/**
 * Created by mhauk on 5/29/2017.
 */

public class BackBat extends Conversion {

    Conversion width, length, fWidth, overage;

    private final double CMTOYARDRATIO = 36 * INCHTOCMRATIO; // the ratio between yard and centimeter
    private final double INCHTOYARDRATIO = 36;


    //constructors
    BackBat() {
        width = new Conversion();
        length = new Conversion();
        fWidth = new Conversion();
        overage = new Conversion();
    }
    BackBat(Conversion newWidth, Conversion newLength, Conversion newfWidth, Conversion newOverage) {
        width = newWidth;
        length = newLength;
        fWidth = newfWidth;
        overage = newOverage;
    }

    // Getters
    public Conversion getWidth() {
        return width;
    }

    public Conversion getLength() {
        return length;
    }

    public Conversion getFWidth() {
        return fWidth;
    }

    public Conversion getOverage() {
        return overage;
    }

    // setters
    public void setWidth(Conversion newWidth) {
        width = newWidth;
    }

    public void setLength(Conversion newLength) {
        length = newLength;
    }

    public void setFWidth(Conversion newFWidth) {
        fWidth = newFWidth;
    }

    public void setOverage(Conversion newOverage) {
        overage = newOverage;
    }

    // inherited methods

    @Override
    public void setCm(double nCm) {
        cm = nCm;
        rationalInch.setRational(cm / INCHTOCMRATIO);
        rationalYard.setRational(cm / CMTOYARDRATIO);
    }

    @Override
    public void setRationalInch(int numerator, int denominator, int whole){
        rationalInch.setRational(numerator, denominator, whole);
        rationalInch.setDecimal(numerator, denominator, whole);
        cm = rationalInch.getDecimal() * INCHTOCMRATIO;

        Fractions f = new Fractions();
        f.setRational((int)(whole % INCHTOYARDRATIO), (int)INCHTOYARDRATIO, 0);
        whole /= INCHTOYARDRATIO;

        rationalYard.setRational(numerator, denominator * (int)INCHTOYARDRATIO, whole);
        rationalYard.add(f);
    }

/*    @Override
    public void setRationalYard(int numerator, int denominator, int whole) {
        rationalYard.setDecimal(numerator, denominator, whole);
        rationalYard.setRational(numerator, denominator, whole);
        yard = rationalYard.getDecimal();

        numerator *= INCHTOYARDRATIO;
        whole *= INCHTOYARDRATIO;
        rationalInch.setRational(numerator, denominator, whole);
        rationalInch.setDecimal();

        cm = rationalInch.getDecimal() * INCHTOCMRATIO;
    }
*/
    // Methods

    // 2 width methods because the seam thickness is different (?)
    public Conversion findLengthCm () {

        //placeholder data
        Conversion placeholderLength = new Conversion(length);
        Conversion placeholderFWidth = new Conversion(fWidth);
        Conversion placeholderWidth = new Conversion(width);

        // Add in the overage
        // Somehow width and length get altered here
        placeholderWidth.setCm(placeholderWidth.getCm() + (overage.getCm() * 2));
        placeholderLength.setCm(placeholderLength.getCm() + (overage.getCm() * 2));

        while(placeholderWidth.getCm() > placeholderFWidth.getCm()) {

            // Increment of one length per seam
            // fWidth also increases to say when to stop
            placeholderLength.setCm(placeholderLength.getCm() + placeholderLength.getCm());
            placeholderFWidth.setCm(placeholderFWidth.getCm() + placeholderWidth.getCm() + 2);
        }

        return placeholderLength;
    }//findLengthCm

    public Conversion findLengthInch() {

        //placeholder data
        Conversion placeholderLength = new Conversion(length);
        Conversion placeholderFWidth = new Conversion(fWidth);
        Conversion placeholderWidth = new Conversion(width);

        overage.setRationalInch(overage.getRationalInch().multiply(2));


        // Add in the overage'
        placeholderWidth.setRationalInch(placeholderWidth.getRationalInch().add(overage.getRationalInch()));

        placeholderLength.setRationalInch(placeholderLength.getRationalInch().add(overage.getRationalInch()));

        while(placeholderWidth.getRationalInch().compare(placeholderFWidth.getRationalInch()) == 1) {

            // Increment of one length per seam
            // width also increases to say when to stop
            placeholderLength.setRationalInch(placeholderLength.getRationalInch().add(length.getRationalInch()));

            placeholderFWidth.getRationalInch().add(placeholderWidth.getRationalInch());
            placeholderFWidth.getRationalInch().add(1);
        }

        return placeholderLength;
    }//findLengthInch


    public String toString() {
        return "Width: " + getWidth() + "\nLength: " + getLength() +
                "\nfabric width: " + getFWidth() + "\noverage: " + getOverage();
    }
}// class