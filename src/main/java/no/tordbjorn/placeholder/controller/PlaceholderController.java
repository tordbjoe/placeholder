package no.tordbjorn.placeholder.controller;

import no.tordbjorn.placeholder.model.PNGPlaceholderOptions;
import no.tordbjorn.placeholder.service.PlaceholderGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaceholderController {

    private PlaceholderGeneratorService placeholderGeneratorService;

    @Autowired
    public PlaceholderController(PlaceholderGeneratorService placeholderGeneratorService) {
        this.placeholderGeneratorService = placeholderGeneratorService;
    }
    @GetMapping(path = "/placeholder/{width}/{height}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generatePNGPlaceholder(@PathVariable("width") int width, @PathVariable("height") int height) throws Exception{
        PNGPlaceholderOptions pngPlaceholderOptions = new PNGPlaceholderOptions(width, height);
        return placeholderGeneratorService.generatePlaceholder(pngPlaceholderOptions);
    }
}
