package com.magnus.oskar.quiltingcalc.calculations;

/**
 * Created by Magnus on 5/29/2017.
 */

public class BackBat extends Conversion {

    private Conversion width, length, fWidth, overage;
    private Conversion neededLength = new Conversion();
    private Conversion reverseNeededLength = new Conversion();

    private final double CMTOYARDRATIO = 36 * INCHTOCMRATIO; // the ratio between yard and centimeter
    private final double INCHTOYARDRATIO = 36;


    //constructors
    public BackBat() {
        width = new Conversion();
        length = new Conversion();
        fWidth = new Conversion();
        overage = new Conversion();
    }
    public BackBat(Conversion newWidth, Conversion newLength, Conversion newfWidth, Conversion newOverage) {
        width = newWidth;
        length = newLength;
        fWidth = newfWidth;
        overage = newOverage;
    }

    // Getters
    private Conversion getWidth() {
        return width;
    }

    private Conversion getLength() {
        return length;
    }

    private Conversion getFWidth() {
        return fWidth;
    }

    private Conversion getOverage() {
        return overage;
    }

    private Conversion getNeededLength() { return neededLength; }

    private Conversion getReverseNeededLength() { return reverseNeededLength; }

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
    public void findLengthCm () {

        //placeholder data
        Conversion placeholderLength = new Conversion(length);
        Conversion placeholderFWidth = new Conversion(fWidth);
        Conversion placeholderWidth = new Conversion(width);

        // Add in the overage
        placeholderWidth.setCm(placeholderWidth.getCm() + (overage.getCm() * 2));
        neededLength.setCm(length.getCm() + (overage.getCm() * 2));
        reverseNeededLength.setCm(width.getCm() + (overage.getCm() * 2));

        while(placeholderWidth.getCm() > placeholderFWidth.getCm()) {

            // Increment of one length per seam
            // fWidth also increases to say when to stop
            neededLength.setCm(neededLength.getCm() + neededLength.getCm());
            placeholderFWidth.setCm(placeholderFWidth.getCm() + placeholderWidth.getCm() + 2);
        }

        placeholderFWidth.setCm(fWidth.getCm());

        while(placeholderLength.getCm() > placeholderFWidth.getCm()) {

            // Increment of one length per seam
            // fWidth also increases to say when to stop
            reverseNeededLength.setCm(reverseNeededLength.getCm() + reverseNeededLength.getCm());
            placeholderFWidth.setCm(placeholderFWidth.getCm() + placeholderLength.getCm() + 2);
        }
    }//findLengthCm

    public void findLengthInch() {

        //placeholder data
        Conversion placeholderLength = new Conversion(length);
        Conversion placeholderFWidth = new Conversion(fWidth);
        Conversion placeholderWidth = new Conversion(width);

        overage.setRationalInch(overage.getRationalInch().multiply(2));


        // Add in the overage'
        placeholderWidth.setRationalInch(placeholderWidth.getRationalInch().add(overage.getRationalInch()));

        neededLength.setRationalInch(length.getRationalInch().add(overage.getRationalInch()));

        while(placeholderWidth.getRationalInch().compare(placeholderFWidth.getRationalInch()) == 1) {

            // Increment of one length per seam
            // width also increases to say when to stop
            neededLength.setRationalInch(neededLength.getRationalInch().add(length.getRationalInch()));

            placeholderFWidth.getRationalInch().add(placeholderWidth.getRationalInch());
            placeholderFWidth.getRationalInch().add(1);
        }

        placeholderFWidth = new Conversion(fWidth);

        while(placeholderLength.getRationalInch().compare(placeholderFWidth.getRationalInch()) == 1) {

            // Increment of one length per seam
            // width also increases to say when to stop
            reverseNeededLength.setRationalInch(placeholderWidth.getRationalInch().add(width.getRationalInch()));

            placeholderFWidth.getRationalInch().add(placeholderLength.getRationalInch());
            placeholderFWidth.getRationalInch().add(1);
        }
    }//findLengthInch


    public String toString() {
        return "Width: " + getWidth() + "\nLength: " + getLength() +
                "\nfabric width: " + getFWidth() + "\noverage: " + getOverage() +
                "\nNeeded Length: "+ getNeededLength() + "\nreverse: " + getReverseNeededLength();
    }
}// class