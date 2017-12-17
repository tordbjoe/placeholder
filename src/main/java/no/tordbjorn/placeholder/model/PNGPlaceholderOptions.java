package no.tordbjorn.placeholder.model;

public class PNGPlaceholderOptions extends PlaceholderOptions {

    private static final String type = "png";

    public PNGPlaceholderOptions(int width, int height) {
        super(width, height);
    }

    public String getType() {
        return type;
    }
}
