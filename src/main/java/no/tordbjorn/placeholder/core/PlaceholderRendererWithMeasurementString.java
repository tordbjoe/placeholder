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
public class PlaceholderRendererWithMeasurementString implements PlaceholderRenderer {

    private static final Logger LOG = LoggerFactory.getLogger(PlaceholderRendererWithX.class);

    @Override
    public byte[] renderPlaceholder(PlaceholderOptions placeholderOptions) throws Exception {
        LOG.info("Incoming request to render placeholder with the following options: {}", placeholderOptions);

        BufferedImage bufferedImageResult = null;

        bufferedImageResult = new BufferedImage(placeholderOptions.getWidth(), placeholderOptions.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D graphics2D = bufferedImageResult.createGraphics();
        graphics2D.setBackground(Color.LIGHT_GRAY);
        graphics2D.clearRect(0, 0, placeholderOptions.getWidth(), placeholderOptions.getHeight());
        graphics2D.setColor(Color.GRAY);

        Font font = new Font(Font.MONOSPACED, Font.BOLD, 60);
        graphics2D.setFont(font);

        String message = placeholderOptions.getWidth() + " x " + placeholderOptions.getHeight();

        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int stringWdith = fontMetrics.stringWidth(message);
        int stringHeight = fontMetrics.getAscent();

        graphics2D.drawString(message, (placeholderOptions.getWidth() - stringWdith) / 2, placeholderOptions.getWidth() / 2 + stringHeight / 4);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        if (placeholderOptions instanceof JPGPlaceholderOptions) {
            ImageIO.write(bufferedImageResult, ((JPGPlaceholderOptions) placeholderOptions).getType(), byteArrayOutputStream);
        } else if (placeholderOptions instanceof PNGPlaceholderOptions) {
            ImageIO.write(bufferedImageResult, ((PNGPlaceholderOptions) placeholderOptions).getType(), byteArrayOutputStream);
        } else {
            throw new Exception("PlaceholderOptions must implement a valid type property");
        }

        return byteArrayOutputStream.toByteArray();
    }
}
