package no.tordbjorn.placeholder.service;

import no.tordbjorn.placeholder.model.PlaceholderOptions;
import org.springframework.stereotype.Service;

@Service
public interface PlaceholderGeneratorService {

    public byte[] generatePlaceholder(PlaceholderOptions placeholderOptions) throws Exception;
}
