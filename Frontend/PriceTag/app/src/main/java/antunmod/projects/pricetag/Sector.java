package antunmod.projects.pricetag;

import java.io.Serializable;

/**
 * Created by antun on 1/11/2018.
 */

public class Sector implements Serializable {

    private int sectorId;
    private String sectorName;

    public Sector() {
    }

    public Sector(int sectorId, String sectorName) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
    }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }
}
