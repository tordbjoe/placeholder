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
}
