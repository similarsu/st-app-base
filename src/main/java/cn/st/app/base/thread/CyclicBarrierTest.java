package cn.st.app.base.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * desciption:模拟等人到齐统一出发问题
 * </p>
 * 
 * @author coolearth
 * @date 2015年9月11日
 */
public class CyclicBarrierTest {
    private static int count = 3;

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CyclicBarrier cb = new CyclicBarrier(count);
        for (int i = 0; i < count; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName()
                                + "即将到达集合地点1，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，"
                                + (cb.getNumberWaiting() == count - 1 ? "都到齐了，继续走啊" : "正在等候"));
                        cb.await();

                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName()
                                + "即将到达集合地点2，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，"
                                + (cb.getNumberWaiting() == count - 1 ? "都到齐了，继续走啊" : "正在等候"));
                        cb.await();
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程" + Thread.currentThread().getName()
                                + "即将到达集合地点3，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，"
                                + (cb.getNumberWaiting() == count - 1 ? "都到齐了，继续走啊" : "正在等候"));
                        cb.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        service.shutdown();
    }
}
