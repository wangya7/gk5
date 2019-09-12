package wang.bannong.gk5.administrative.model;

/**
 * Created by bn. on 2019/4/4 4:07 PM
 */
public class City extends Administrative {
    private static final long serialVersionUID = 8528912342713739857L;
    private int provinceCode;

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Override
    public String toString() {
        return "City{" +
                "provinceCode=" + provinceCode +
                "} " + super.toString();
    }
}
