package antunmod.projects.pricetag.model;

import android.graphics.Bitmap;

/*
    A class for images in GridView shown in SearchFragment.
 */
public class ImageItem {

    private Bitmap image;
    private String text;

    public ImageItem() {}

    public ImageItem(Bitmap image, String text) {
        this.image = image;
        this.text = text;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(Bitmap bitmap) {
        this.image = image;
    }
}
