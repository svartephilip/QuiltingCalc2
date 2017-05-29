package com.magnus.oskar.quiltingcalc;

/**
 * Created by mhauk on 5/29/2017.
 */

public class BackBat {

    Conversion width, length, fWidth;

    //constructors
    BackBat() {
        width = new Conversion();
        length = new Conversion();
        fWidth = new Conversion();
    }
    BackBat(Conversion newWidth, Conversion newLength, Conversion newfWidth) {
        width = newWidth;
        length = newLength;
        fWidth = newfWidth;
    }
}
