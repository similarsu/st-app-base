package cn.st.app.base.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 * <p>
 * desciption:信号灯
 * </p>
 * 
 * @author coolearth
 * @date 2015年9月9日
 */
public class SemaphoreTest {
    private static int count = 3;

    /**
     * 模拟10个人同时上厕所，但是只有3个坑位
     * 
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore sp = new Semaphore(count);
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        sp.acquire();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    System.out.println("线程" + Thread.currentThread().getName() + "进入，当前"
                            + (count - sp.availablePermits()) + "/" + count);
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() + "离开，当前"
                            + (count - sp.availablePermits() - 1) + "/" + count);

                    sp.release();
                    // 下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
                    // System.out.println("线程" + Thread.currentThread().getName() + "已离开,当前已有"
                    // + (3 - sp.availablePermits()) + "个并发");
                }
            };
            service.execute(runnable);
        }
        service.shutdown();
    }

}
