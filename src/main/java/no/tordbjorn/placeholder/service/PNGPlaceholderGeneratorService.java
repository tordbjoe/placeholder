package no.tordbjorn.placeholder.service;

import no.tordbjorn.placeholder.core.PlaceholderRendererWithMeasurementString;
import no.tordbjorn.placeholder.model.PlaceholderOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pngService")
public class PNGPlaceholderGeneratorService implements PlaceholderGeneratorService {

    @Autowired
    private PlaceholderRendererWithMeasurementString placeholderRenderer;

    @Override
    public byte[] generatePlaceholder(PlaceholderOptions placeholderOptions) throws Exception {
        return placeholderRenderer.renderPlaceholder(placeholderOptions);
    }
}
