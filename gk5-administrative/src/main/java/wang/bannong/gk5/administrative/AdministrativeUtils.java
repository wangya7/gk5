package wang.bannong.gk5.administrative;

import com.google.common.io.ByteStreams;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import wang.bannong.gk5.util.json.Json;

/**
 * 政区工具类
 * Created by bn. on 2019/4/4 4:05 PM
 */
@Component
public final class AdministrativeUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdministrativeUtils.class);

    private static List<Province> provinces = null;
    private static List<City>     cities = null;
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
            provinces = Json.toJavaList(ps, Province.class);
            for (Province item : provinces) {
                administrativeMap.put(item.getCode(), item);
            }

            String cs = new String(ByteStreams.toByteArray(cl.getResourceAsStream("cities.json")));
            cities = Json.toJavaList(cs, City.class);
            Map<Integer, City> cityMap = new HashMap<>();
            for (City item : cities) {
                administrativeMap.put(item.getCode(), item);
                cityMap.put(item.getCode(), item);
            }
            cityMapByProvinceCode = cities.stream().collect(Collectors.groupingBy(i -> i.getProvince().getCode()));

            String as = new String(ByteStreams.toByteArray(cl.getResourceAsStream("areas.json")));
            areas = Json.toJavaList(as, Area.class);
            for (Area item : areas) {
                administrativeMap.put(item.getCode(), item);
            }

            // NOTICE: pca-code.json 中的地区在 areas.json中可能不存在，比如"东莞市"下面没有区，直接是街道，需要再次补充街道信息
            String ss = new String(ByteStreams.toByteArray(cl.getResourceAsStream("streets.json")));
            List<Street> streets = Json.toJavaList(ss, Street.class);
            for (Street item : streets) {
                administrativeMap.put(item.getCode(), item);
            }

            areaMapByCityCode = areas.stream().collect(Collectors.groupingBy(i -> i.getCity().getCode()));

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
    public static Administrative of(Integer code) {
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
    public static String getProvinceArray() {
        return Json.toJson(provinces);
    }

    /** 获取所有省级（省份直辖市自治区）列表 */
    public static List<Province> getProvinces() {
        return provinces;
    }

    /**
     * 根据城市code获取省
     *
     * @param cityCode 城市code
     * @return
     */
    public static Province getProvince(final Integer cityCode) {
        Administrative administrative = administrativeMap.get(cityCode);
        if (administrative != null && administrative instanceof City) {
            City area = (City) administrative;
            return area.getProvince();
        }
        return null;
    }

    /** 获取所有地级（城市）JSON对象 */
    public static String getCityArray() {
        return Json.toJson(cities);
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
    public static List<City> getCities(final Integer provinceCode) {
        return cityMapByProvinceCode.get(provinceCode);
    }

    /**
     * 跟进区code获取城市
     *
     * @param areaCode 区code
     * @return
     */
    public static City getCity(final Integer areaCode) {
        Administrative administrative = administrativeMap.get(areaCode);
        if (administrative != null && administrative instanceof Area) {
            Area area = (Area) administrative;
            return area.getCity();
        }
        return null;
    }

    /** 获取所有县级（区县）JSON对象 */
    public static String getAreaArray() {
        return Json.toJson(areas);
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
    public static List<Area> getAreas(final Integer cityCode) {
        return areaMapByCityCode.get(cityCode);
    }

    public static City queryByPlate(String plate) {
        return plateAdministrativeMap.get(plate);
    }

}
