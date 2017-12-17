package no.tordbjorn.placeholder.model;

public class JPGPlaceholderOptions extends PlaceholderOptions implements TypeProvider {

    private static final String type = "jpg";

    public JPGPlaceholderOptions(int width, int height) {
        super(width, height);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "JPGPlaceholderOptions{type="+type+"} " + super.toString();
    }
}
