package com.magnus.oskar.quiltingcalc;

import com.magnus.oskar.quiltingcalc.calculations.BackBat;
import com.magnus.oskar.quiltingcalc.calculations.Conversion;

/**
 * Created by Magnus on 23.08.2017.
 */

public interface BackBatDataPasser extends PassData {
    void calculationPassing(BackBat data);
    void measurementPassing(Conversion[] data);
}
