package cn.st.app.base.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * <p>
 * desciption:阻塞队列
 * </p>
 * 
 * @author coolearth
 * @date 2015年9月14日
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);// 该队列里面只能放3个Integer
        for (int i = 0; i < 2; i++) {
            new Thread() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep((long) (Math.random() * 1000));
                            System.out.println(Thread.currentThread().getName() + "准备放数据!");
                            queue.put(1);// 如果放满就会阻塞
                            System.out.println(Thread.currentThread().getName() + "已经放了数据，"
                                    + "队列目前有" + queue.size() + "个数据");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        // 将此处的睡眠时间分别改为100和1000，观察运行结果
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "准备取数据!");
                        queue.take();// 如果没有了数据,就会阻塞
                        System.out.println(Thread.currentThread().getName() + "已经取走数据，" + "队列目前有"
                                + queue.size() + "个数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();
    }
}
