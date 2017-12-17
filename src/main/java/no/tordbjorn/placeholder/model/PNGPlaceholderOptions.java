package no.tordbjorn.placeholder.model;

public class PNGPlaceholderOptions extends PlaceholderOptions implements TypeProvider{

    private static final String type = "png";

    public PNGPlaceholderOptions(int width, int height) {
        super(width, height);
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "PNGPlaceholderOptions{type="+type+"} " + super.toString();
    }
}
