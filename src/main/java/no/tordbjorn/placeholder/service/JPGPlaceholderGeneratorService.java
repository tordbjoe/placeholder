package no.tordbjorn.placeholder.service;

import no.tordbjorn.placeholder.model.JPGPlaceholderOptions;
import no.tordbjorn.placeholder.model.PlaceholderOptions;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service("jpgService")
public class JPGPlaceholderGeneratorService implements PlaceholderGeneratorService{

    @Override
    public byte[] generatePlaceholder(PlaceholderOptions placeholderOptions) throws Exception {
        BufferedImage bufferedImageResult = null;
        if (placeholderOptions instanceof JPGPlaceholderOptions) {
            bufferedImageResult = new BufferedImage(placeholderOptions.getWidth(), placeholderOptions.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

            Graphics2D graphics2D = bufferedImageResult.createGraphics();
            graphics2D.setBackground(Color.GRAY);
            graphics2D.clearRect(0,0, placeholderOptions.getWidth(), placeholderOptions.getHeight());
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawLine(0,0, placeholderOptions.getWidth(), placeholderOptions.getHeight());
            graphics2D.drawLine(placeholderOptions.getWidth(), 0, 0, placeholderOptions.getHeight());

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImageResult,  ((JPGPlaceholderOptions) placeholderOptions).getType(), byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } else {
            throw new Exception("Placeholder option for JPG must be of type JPGPlaceholderOptions");
        }

    }
}
