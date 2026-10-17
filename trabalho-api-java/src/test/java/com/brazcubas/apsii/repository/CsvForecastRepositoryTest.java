package com.brazcubas.apsii.repository;

import com.brazcubas.apsii.model.Forecast;
import com.brazcubas.apsii.model.PredictionModel;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CsvForecastRepositoryTest {
    @Test
    void loadsOnlyRowsWithValidOptionalModelConfidence() {
        ModelRepository models = mock(ModelRepository.class);
        when(models.findAll()).thenReturn(List.of(
                model("model-a"),
                model("model-b")));

        CsvForecastRepository repository = new CsvForecastRepository(
                new DefaultResourceLoader(),
                models,
                "classpath:data-test/predictions-11-2.csv");
        repository.load();

        List<Forecast> all = repository.findAll();
        List<Forecast> modelATimeline = repository.findByModelAndProbe("model-a", 900001);

        assertEquals(5, all.size());
        assertEquals(4, modelATimeline.size());
        assertNull(modelATimeline.get(1).modelConfidence());
        assertEquals(0.0, modelATimeline.get(2).modelConfidence());
        assertEquals(1.0, modelATimeline.get(3).modelConfidence());
        assertEquals(List.of("mock-a-1", "mock-a-2", "mock-a-3", "mock-a-4"),
                modelATimeline.stream().map(Forecast::predictionId).toList());
    }

    private static PredictionModel model(String id) {
        return new PredictionModel(id, "Modelo " + id, "Descrição", "Grupo", "Teste", "1.0", true, null);
    }
}
