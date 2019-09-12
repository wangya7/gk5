package wang.bannong.gk5.administrative;

import com.google.common.io.ByteStreams;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import wang.bannong.gk5.administrative.model.Administrative;
import wang.bannong.gk5.administrative.model.Area;
import wang.bannong.gk5.administrative.model.City;
import wang.bannong.gk5.administrative.model.Plate;
import wang.bannong.gk5.administrative.model.Province;
import wang.bannong.gk5.administrative.model.Street;

/**
 * 政区工具类
 * Created by bn. on 2019/4/4 4:05 PM
 */
@Component
public final class AdministrativeUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdministrativeUtils.class);

    private static JSONArray      provinceArray = null;
    private static List<Province> provinces = null;

    private static JSONArray      cityArray = null;
    private static List<City>     cities = null;

    private static JSONArray      areaArray = null;
    private static List<Area>     areas = null;

    private static Map<Integer, List<City>>     cityMapByProvinceCode   = new HashMap<>();
    private static Map<Integer, List<Area>>     areaMapByCityCode       = new HashMap<>();
    private static Map<Integer, Administrative> administrativeMap       = new HashMap<>();

    // 车牌城市关联 注意4个直辖市
    private static Map<String, City>            plateAdministrativeMap  = new HashMap<>();

    static {
        ClassLoader cl = AdministrativeUtils.class.getClassLoader();
        try {
            String ps = new String(ByteStreams.toByteArray(cl.getResourceAsStream("provinces.json")));
            provinceArray = JSON.parseArray(ps);
            provinces = JSON.parseArray(ps, Province.class);
            for (Province item : provinces) {
                administrativeMap.put(item.getCode(), item);
            }

            String cs = new String(ByteStreams.toByteArray(cl.getResourceAsStream("cities.json")));
            cityArray = JSON.parseArray(cs);
            cities = JSON.parseArray(cs, City.class);
            Map<Integer, City> cityMap = new HashMap<>();
            for (City item : cities) {
                administrativeMap.put(item.getCode(), item);
                cityMap.put(item.getCode(), item);
            }
            cityMapByProvinceCode = cities.stream().sequential().collect(Collectors.groupingBy(City::getProvinceCode));

            String as = new String(ByteStreams.toByteArray(cl.getResourceAsStream("areas.json")));
            areaArray = JSON.parseArray(as);
            areas = JSON.parseArray(as, Area.class);
            for (Area item : areas) {
                administrativeMap.put(item.getCode(), item);
            }

            // NOTICE: pca-code.json 中的地区在 areas.json中可能不存在，比如"东莞市"下面没有区，直接是街道，需要再次补充街道信息
            String ss = new String(ByteStreams.toByteArray(cl.getResourceAsStream("streets.json")));
            List<Street> streets = JSON.parseArray(ss, Street.class);
            for (Street item : streets) {
                administrativeMap.put(item.getCode(), item);
            }

            // 车牌信息
            String pcp = new String(ByteStreams.toByteArray(cl.getResourceAsStream("pcplate.json")));
            JSONArray array = JSON.parseArray(pcp);
            int length = array.size();
            for (int i = 0; i < length; i++) {
                JSONObject province = array.getJSONObject(i);
                int provinceCode = province.getIntValue("code");
                if (provinceCode == 11 || provinceCode == 12 || provinceCode == 31 || provinceCode == 50) {
                    plateAdministrativeMap.put(province.getString("plate"), cityMap.get(provinceCode * 100 + 1));
                } else {
                    List<Plate> plates = JSON.parseArray(province.getString("children"), Plate.class);
                    for (Plate plate : plates) {
                        if (plate.getPlate() != null && plate.getPlate().size() > 0) {
                            List<String> items = plate.getPlate();
                            for (String item : items) {
                                plateAdministrativeMap.put(item, cityMap.get(plate.getCode()));
                            }
                        }
                    }
                }
            }

            areaMapByCityCode = areas.stream().sequential().collect(Collectors.groupingBy(Area::getCityCode));

            LOGGER.info("行政区：省级（省份直辖市自治区）、 地级（城市）、 县级（区县）加载完成 ");
        } catch (IOException e) {
            LOGGER.error("行政区：省级（省份直辖市自治区）、 地级（城市）、 县级（区县）加载失败, exception detail:", e);
        }
    }

    /**
     * 根据code获取政区模型
     *
     * @param code 政区code
     * @return
     */
    public static Administrative of(int code) {
        return administrativeMap.get(code);
    }

    /**
     * 根据codes获取政区模型
     *
     * @param codes 政区code集合
     * @return
     */
    public static Map<Integer, Administrative> of(List<Integer> codes) {
        Map<Integer, Administrative> map = new HashMap<>();
        for (Integer code : codes) {
            map.put(code, administrativeMap.get(code));
        }
        return map;
    }



    /** 获取所有省级（省份直辖市自治区）JSON对象 */
    public static JSONArray getProvinceArray() {
        return provinceArray;
    }

    /** 获取所有省级（省份直辖市自治区）列表 */
    public static List<Province> getProvinces() {
        return provinces;
    }

    /**
     * 跟进城市code获取省
     *
     * @param cityCode 城市code
     * @return
     */
    public static Province getProvince(final int cityCode) {
        return getProvinces().parallelStream()
                .filter(ii -> getCities().parallelStream()
                        .filter(i -> i.getCode() == cityCode)
                        .findFirst()
                        .get().getProvinceCode() == ii.getCode())
                .findFirst().get();
    }

    /** 获取所有地级（城市）JSON对象 */
    public static JSONArray getCityArray() {
        return cityArray;
    }

    /** 获取所有地级（城市）列表 */
    public static List<City> getCities() {
        return cities;
    }

    /**
     * 获取指定地级（城市）列表
     *
     * @param provinceCode 省code
     * @return
     */
    public static List<City> getCities(final int provinceCode) {
        return cityMapByProvinceCode.get(provinceCode);
    }

    /**
     * 跟进区code获取城市
     *
     * @param areaCode 区code
     * @return
     */
    public static City getCity(final int areaCode) {
        return getCities().parallelStream()
                .filter(ii -> getAreas().parallelStream()
                        .filter(i -> i.getCode() == areaCode)
                        .findFirst()
                        .get().getCityCode() == ii.getCode())
                .findFirst().get();
    }

    /** 获取所有县级（区县）JSON对象 */
    public static JSONArray getAreaArray() {
        return areaArray;
    }

    /** 获取所有县级（区县）列表 */
    public static List<Area> getAreas() {
        return areas;
    }

    /**
     * 获取指定的县级（区县）列表
     *
     * @param cityCode 城市code
     * @return
     */
    public static List<Area> getAreas(final int cityCode) {
        return areaMapByCityCode.get(cityCode);
    }

    public static City queryByPlate(String plate) {
        return plateAdministrativeMap.get(plate);
    }

}
