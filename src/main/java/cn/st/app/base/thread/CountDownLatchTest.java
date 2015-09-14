package cn.st.app.base.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * desciption: 倒计时<br/>
 * 模拟田径比赛<br/>
 * 运动员等待裁判命令起跑<br/>
 * 比赛结束后，结果反馈给裁判<br/>
 * </p>
 * 
 * @author coolearth
 * @date 2015年9月14日
 */
public class CountDownLatchTest {
    private static int count = 3;

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch countAnswer = new CountDownLatch(3);
        final CountDownLatch countOrder = new CountDownLatch(1);
        for (int i = 0; i < count; i++) {
            Runnable runnable = new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        System.out.println(Thread.currentThread().getName() + "等待起跑命令");
                        countOrder.await();
                        System.out.println(Thread.currentThread().getName() + "接收到起跑命令，出发了");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(Thread.currentThread().getName() + "到达终点");
                        countAnswer.countDown();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            };
            service.execute(runnable);
        }
        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println(Thread.currentThread().getName() + "即将发布命令");
            countOrder.countDown();
            System.out.println(Thread.currentThread().getName() + "已发送命令，正在等待结果");
            countAnswer.await();
            System.out.println(Thread.currentThread().getName() + "已收到比赛结果");
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
