package cn.st.app.base.timertask;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>
 * desciption:
 * </p>
 * 
 * @author coolearth
 * @date 2015年8月31日
 */
public class BombTimerTask extends TimerTask {
    private static int count = 0;

    @Override
    public void run() {
        count = (count + 1) % 2;
        System.out.println("bombing");
        new Timer().schedule(new BombTimerTask(), 2000 + 2000 * count);;
    }

}
