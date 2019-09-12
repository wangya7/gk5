package wang.bannong.gk5.administrative.model;

/**
 * Created by bn. on 2019/5/18 3:10 PM
 */
public class Street extends Administrative {
    private static final long serialVersionUID = 6033123034074711987L;
    private int areaCode;
    private int cityCode;
    private int provinceCode;

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

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
        return "Street{" +
                "areaCode=" + areaCode +
                ", cityCode=" + cityCode +
                ", provinceCode=" + provinceCode +
                "} " + super.toString();
    }
}
