package wang.bannong.gk5.log;

/**
 * Created by wang.bannong on 2017/4/12.
 */
public class LogManager {

    private LogManager() {
    }

    public static org.apache.logging.log4j.Logger getLogger(Class clazz) {
        return org.apache.logging.log4j.LogManager.getLogger(clazz.getName());
    }
}
