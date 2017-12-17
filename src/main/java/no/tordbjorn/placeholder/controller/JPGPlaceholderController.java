package no.tordbjorn.placeholder.controller;

import no.tordbjorn.placeholder.model.JPGPlaceholderOptions;
import no.tordbjorn.placeholder.service.JPGPlaceholderGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JPGPlaceholderController {

    private JPGPlaceholderGeneratorService generatePlaceholder;

    @Autowired
    public JPGPlaceholderController(JPGPlaceholderGeneratorService generatePlaceholder) {
        this.generatePlaceholder = generatePlaceholder;
    }

    @GetMapping(path = "/placeholder/jpg/{width}/{height}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] generateJPGPlaceholder(@PathVariable("width") int width, @PathVariable("height") int height) throws Exception{
        JPGPlaceholderOptions jpgPlaceholderOptions = new JPGPlaceholderOptions(width, height);
        return generatePlaceholder.generatePlaceholder(jpgPlaceholderOptions);
    }

}
