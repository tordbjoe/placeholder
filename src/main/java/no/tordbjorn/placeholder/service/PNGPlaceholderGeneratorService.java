package no.tordbjorn.placeholder.service;

import no.tordbjorn.placeholder.model.PNGPlaceholderOptions;
import no.tordbjorn.placeholder.model.PlaceholderOptions;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service("pngService")
public class PNGPlaceholderGeneratorService implements PlaceholderGeneratorService {

    @Override
    public byte[] generatePlaceholder(PlaceholderOptions placeholderOptions) throws Exception{
        BufferedImage bufferedImageResult = null;
        if (placeholderOptions instanceof PNGPlaceholderOptions) {
            bufferedImageResult = new BufferedImage(placeholderOptions.getWidth(), placeholderOptions.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);

            Graphics2D graphics2D = bufferedImageResult.createGraphics();
            graphics2D.setBackground(Color.GRAY);
            graphics2D.clearRect(0,0, placeholderOptions.getWidth(), placeholderOptions.getHeight());
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawLine(0,0, placeholderOptions.getWidth(), placeholderOptions.getHeight());
            graphics2D.drawLine(placeholderOptions.getWidth(), 0, 0, placeholderOptions.getHeight());

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImageResult, ((PNGPlaceholderOptions) placeholderOptions).getType(), byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } else {
            throw new Exception("Placeholder option for PNG must be of type PNGPlaceholderOptions");
        }

    }
}
