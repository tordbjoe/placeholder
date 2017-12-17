package no.tordbjorn.placeholder.core;

import no.tordbjorn.placeholder.model.JPGPlaceholderOptions;
import no.tordbjorn.placeholder.model.PNGPlaceholderOptions;
import no.tordbjorn.placeholder.model.PlaceholderOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Component
public class PlaceholderRenderer {

    private static final Logger LOG = LoggerFactory.getLogger(PlaceholderRenderer.class);

    public byte[] renderPlaceholderWithX(PlaceholderOptions placeholderOptions) throws Exception {

        LOG.info("Incoming request to render placeholder with the following options: {}", placeholderOptions);

        BufferedImage bufferedImageResult = null;

        bufferedImageResult = new BufferedImage(placeholderOptions.getWidth(), placeholderOptions.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D graphics2D = bufferedImageResult.createGraphics();
        graphics2D.setBackground(Color.GRAY);
        graphics2D.clearRect(0, 0, placeholderOptions.getWidth(), placeholderOptions.getHeight());
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawLine(0, 0, placeholderOptions.getWidth(), placeholderOptions.getHeight());
        graphics2D.drawLine(placeholderOptions.getWidth(), 0, 0, placeholderOptions.getHeight());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        if (placeholderOptions instanceof JPGPlaceholderOptions) {
            ImageIO.write(bufferedImageResult, ((JPGPlaceholderOptions) placeholderOptions).getType(), byteArrayOutputStream);
        }
        else if (placeholderOptions instanceof PNGPlaceholderOptions) {
            ImageIO.write(bufferedImageResult, ((PNGPlaceholderOptions) placeholderOptions).getType(), byteArrayOutputStream);
        } else {
            throw new Exception("PlaceholderOptions must implement a valid type property");
        }


        return byteArrayOutputStream.toByteArray();
    }

}
