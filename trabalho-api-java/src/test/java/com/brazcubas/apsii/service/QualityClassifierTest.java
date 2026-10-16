package com.brazcubas.apsii.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QualityClassifierTest {
    private final QualityClassifier classifier = new QualityClassifier();

    @Test
    void classifiesGoodConnection() {
        assertEquals("GOOD", classifier.classify(30.0, 0.0));
    }

    @Test
    void classifiesModeratePacketLoss() {
        assertEquals("MODERATE", classifier.classify(40.0, 1.5));
    }

    @Test
    void worseMetricDeterminesQuality() {
        assertEquals("UNSTABLE", classifier.classify(20.0, 5.0));
    }

    @Test
    void classifiesRttAtUnstableThresholdAsUnstable() {
        assertEquals("UNSTABLE", classifier.classify(150.0, 0.0));
    }

    @Test
    void rejectsNonFiniteRtt() {
        assertThrows(IllegalArgumentException.class, () -> classifier.classify(Double.NaN, 0.0));
    }
}
