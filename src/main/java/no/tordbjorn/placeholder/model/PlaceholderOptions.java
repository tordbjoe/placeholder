package no.tordbjorn.placeholder.model;

public abstract class PlaceholderOptions {

    private int width;
    private int height;

    public PlaceholderOptions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "PlaceholderOptions{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
