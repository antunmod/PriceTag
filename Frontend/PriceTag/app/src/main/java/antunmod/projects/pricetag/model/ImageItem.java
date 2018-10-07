package antunmod.projects.pricetag.model;

import android.graphics.Bitmap;

/*
    A class for images in GridView shown in SearchFragment.
 */
public class ImageItem {

    private Bitmap image;


    public ImageItem(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }


    public void setImage(Bitmap bitmap) {
        this.image = bitmap;
    }
}
