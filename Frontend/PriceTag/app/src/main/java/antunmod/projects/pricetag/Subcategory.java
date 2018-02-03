package antunmod.projects.pricetag;

/**
 * Created by antun on 2/3/2018.
 */

public class Subcategory {

    private int subcategoryId;
    private String subcategoryName;

    public Subcategory() {
    }

    public Subcategory(int subcategoryId, String subcategoryName) {
        this.subcategoryId = subcategoryId;
        this.subcategoryName = subcategoryName;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }
}
