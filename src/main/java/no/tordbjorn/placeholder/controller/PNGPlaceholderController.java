package no.tordbjorn.placeholder.controller;

import no.tordbjorn.placeholder.model.PNGPlaceholderOptions;
import no.tordbjorn.placeholder.service.PNGPlaceholderGeneratorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PNGPlaceholderController {

    private PNGPlaceholderGeneratorService placeholderGeneratorService;

    public PNGPlaceholderController(PNGPlaceholderGeneratorService placeholderGeneratorService) {
        this.placeholderGeneratorService = placeholderGeneratorService;
    }

    @GetMapping(path = "/placeholder/png/{width}/{height}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generatePNGPlaceholder(@PathVariable("width") int width, @PathVariable("height") int height) throws Exception{
        PNGPlaceholderOptions pngPlaceholderOptions = new PNGPlaceholderOptions(width, height);
        return placeholderGeneratorService.generatePlaceholder(pngPlaceholderOptions);
    }


}
