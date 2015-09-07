package cn.st.app.base.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * desciption: 任务池
 * </p>
 * 
 * @author coolearth
 * @date 2015年9月7日
 */
public class SchedulePool {
    public static void main(String[] args) {
        // schedule();
        scheduleAtFixedRate();
    }

    /**
     * 模拟炸弹6s后爆炸，之后每隔2s爆炸一次
     */
    public static void scheduleAtFixedRate() {
        ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(3);
        schedulePool.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("bombing!");
            }
        }, 6, 2, TimeUnit.SECONDS);
    }

    /**
     * 模拟炸弹2s后爆炸
     */
    public static void schedule() {
        ScheduledExecutorService schedulePool = Executors.newScheduledThreadPool(3);
        schedulePool.schedule(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("bombing!");
            }
        }, 2, TimeUnit.SECONDS);
    }


}
