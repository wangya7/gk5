package wang.bannong.gk5.administrative;

/**
 * 中华人民共和国行政区划——地级（城市）
 * <p>
 * Created by bn. on 2019/4/4 4:07 PM
 */
public class City extends Administrative {
    private static final long serialVersionUID = 8528912342713739857L;
    private Province province;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "City{" +
                "province=" + province +
                "} " + super.toString();
    }
}
