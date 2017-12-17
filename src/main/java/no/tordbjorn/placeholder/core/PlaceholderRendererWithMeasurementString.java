package no.tordbjorn.placeholder.core;

import no.tordbjorn.placeholder.model.JPGPlaceholderOptions;
import no.tordbjorn.placeholder.model.PNGPlaceholderOptions;
import no.tordbjorn.placeholder.model.PlaceholderOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;


@Component
public class PlaceholderRendererWithMeasurementString implements PlaceholderRenderer {

    private static final Logger LOG = LoggerFactory.getLogger(PlaceholderRendererWithX.class);

    @Override
    public byte[] renderPlaceholder(PlaceholderOptions placeholderOptions) throws Exception {
        LOG.info("Incoming request to render placeholder with the following options: {}", placeholderOptions);

        BufferedImage bufferedImageResult = null;

        bufferedImageResult = new BufferedImage(placeholderOptions.getWidth(), placeholderOptions.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D graphics2D = bufferedImageResult.createGraphics();

        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        graphics2D.setBackground(Color.LIGHT_GRAY);
        graphics2D.clearRect(0, 0, placeholderOptions.getWidth(), placeholderOptions.getHeight());
        graphics2D.setColor(Color.GRAY);

        String message = placeholderOptions.getWidth() + " x " + placeholderOptions.getHeight();
        Font font = Font.decode(Font.MONOSPACED);
        Rectangle2D rectangle2D = graphics2D.getFontMetrics(font).getStringBounds(message, graphics2D);
        font = font.deriveFont((float) (font.getSize() * 100 / rectangle2D.getWidth()));
        graphics2D.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Map<TextAttribute, Object> atts = new HashMap<TextAttribute, Object>();
        atts.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
        font = font.deriveFont(atts);
        graphics2D.setFont(font);

        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int stringWdith = fontMetrics.stringWidth(message);
        int stringHeight = fontMetrics.getAscent();

        graphics2D.drawString(message, (placeholderOptions.getWidth() - stringWdith) / 2, placeholderOptions.getHeight() / 2 + stringHeight / 2);

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
