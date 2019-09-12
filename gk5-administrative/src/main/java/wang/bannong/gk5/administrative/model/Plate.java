package wang.bannong.gk5.administrative.model;

import java.util.List;

/**
 * Created by bn. on 2019/5/21 1:51 PM
 */
public class Plate extends Administrative {
    private static final long serialVersionUID = 1618146553304404691L;
    private List<String> plate;

    public List<String> getPlate() {
        return plate;
    }

    public void setPlate(List<String> plate) {
        this.plate = plate;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "plate=" + plate +
                "} " + super.toString();
    }
}
