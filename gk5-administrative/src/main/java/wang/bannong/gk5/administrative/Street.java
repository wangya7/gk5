package wang.bannong.gk5.administrative;

/**
 * 中华人民共和国行政区划——乡级（乡镇、街道）
 * <p>
 * Created by bn. on 2019/5/18 3:10 PM
 */
public class Street extends Administrative {
    private static final long serialVersionUID = 6033123034074711987L;

    private Area area;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Street{" +
                "area=" + area +
                "} " + super.toString();
    }
}
