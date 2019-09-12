package wang.bannong.gk5.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskExecutor {

    private static Logger logger = LoggerFactory.getLogger(TaskExecutor.class);

    private static final int          NUM_OF_PROCESSOR = Runtime.getRuntime().availableProcessors();

    private static final int          MULTIPLIER       = 10;

    private static ThreadPoolExecutor fastThreadPool   = 
            new ThreadPoolExecutor(
                    NUM_OF_PROCESSOR * MULTIPLIER, 
                    NUM_OF_PROCESSOR * MULTIPLIER, 
                    60L, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<Runnable>(1000),
                    new ThreadPoolExecutor.CallerRunsPolicy());
    
    private static ThreadPoolExecutor slowThreadPool   = 
            new ThreadPoolExecutor(
                    NUM_OF_PROCESSOR * MULTIPLIER, 
                    NUM_OF_PROCESSOR * MULTIPLIER, 
                    60L, TimeUnit.MILLISECONDS, 
                    new ArrayBlockingQueue<Runnable>(1000),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 一些不耗时的任务
     * 
     * @param run
     */
    public static void excute(Runnable run) {
        try {
            fastThreadPool.execute(run);
        } catch (Throwable e) {
            logger.error("fast task executed exception.",e);
        }
        if (logger.isInfoEnabled()) {
            logger.info(">>>>>fastThreadPool,active count:{},queue size:{}",
                    fastThreadPool.getActiveCount(),
                    fastThreadPool.getQueue().size());
        }
    }

    /**
     * 一些耗时的操作，如图片上传
     * 
     * @param run
     */
    public static void excuteSlowly(Runnable run) {
        try {
            slowThreadPool.execute(run);
        } catch (Throwable e) {
            logger.error("slow task executed exception.",e);
        }
        
        if (logger.isInfoEnabled()) {
            logger.info(">>>>>slowThreadPool,active count:{},queue size:{}",
                    slowThreadPool.getActiveCount(),
                    slowThreadPool.getQueue().size());
        }
    }
}
