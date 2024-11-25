package wang.bannong.gk5.administrative;

/**
 * 中华人民共和国行政区划——县级（区县）
 * <p>
 * Created by bn. on 2019/4/4 4:07 PM
 */
public class Area extends Administrative {
    private static final long serialVersionUID = -31200209980445216L;
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Area{" +
                "city=" + city +
                "} " + super.toString();
    }
}
