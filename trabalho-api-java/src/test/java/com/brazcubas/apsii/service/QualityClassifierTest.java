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
        double rttAtThreshold = 150.0;
        double packetLoss = 0.0;

        String quality = classifier.classify(rttAtThreshold, packetLoss);

        assertEquals("UNSTABLE", quality);
    }

    @Test
    void rejectsNonFiniteRtt() {
        double nonFiniteRtt = Double.NaN;
        double packetLoss = 0.0;

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> classifier.classify(nonFiniteRtt, packetLoss)
        );

        assertEquals("Prediction metrics must be finite and non-negative", exception.getMessage());
    }
}
