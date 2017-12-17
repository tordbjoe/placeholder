package no.tordbjorn.placeholder.core;

import no.tordbjorn.placeholder.model.PlaceholderOptions;

public interface PlaceholderRenderer {

    byte[] renderPlaceholder(PlaceholderOptions placeholderOptions) throws Exception;
}
