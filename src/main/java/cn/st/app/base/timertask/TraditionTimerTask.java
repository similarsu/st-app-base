package cn.st.app.base.timertask;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>
 * desciption: 传统定时器任务
 * </p>
 * 
 * @author coolearth
 * @date 2015年8月31日
 */
public class TraditionTimerTask {
    public static void main(String[] args) {
        // schedule1();
        // schedule2();
        schedule3();
    }

    /**
     * 模拟炸弹2s后爆炸
     */
    public static void schedule1() {
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                System.out.println("bombing");
            }
        }, 2 * 1000);
        while (true) {
            System.out.println("current second:" + Calendar.getInstance().get(Calendar.SECOND));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 模拟炸弹2s后爆炸，之后每隔4s爆炸一次
     */
    public static void schedule2() {
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                System.out.println("bombing");
            }
        }, 2 * 1000, 4 * 1000);
        while (true) {
            System.out.println("current second:" + Calendar.getInstance().get(Calendar.SECOND));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 模拟炸弹2s后爆炸，之后隔4s爆炸一次,再隔2s爆炸，再隔4s操作，如此反复
     */
    public static void schedule3() {
        new Timer().schedule(new BombTimerTask(), 2 * 1000);
        while (true) {
            System.out.println("current second:" + Calendar.getInstance().get(Calendar.SECOND));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}
