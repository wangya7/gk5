package wang.bannong.gk5.administrative.model;

/**
 * Created by bn. on 2019/4/4 4:07 PM
 */
public class Area extends Administrative {
    private static final long serialVersionUID = -31200209980445216L;
    private int cityCode;
    private int provinceCode;

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Override
    public String toString() {
        return "Area{" +
                "cityCode=" + cityCode +
                ", provinceCode=" + provinceCode +
                "} " + super.toString();
    }
}
