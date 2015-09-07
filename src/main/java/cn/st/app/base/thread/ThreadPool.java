package cn.st.app.base.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * desciption: 线程池
 * </p>
 * 
 * @author coolearth
 * @date 2015年9月7日
 */
public class ThreadPool {
    public static void main(String[] args) {
        // createFixedThreadPool();
        createCachedThreadPool();
    }

    /**
     * 创建固定大小的线程池
     */
    public static void createFixedThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            threadPool.submit(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName() + " is loop of " + i
                                + " , for task of " + taskId);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                }
            });
        }
        System.out.println("all of 10 tasks have committed");
        // 关闭线程池
        threadPool.shutdown();
    }

    /**
     * 个数可动态改变
     */
    public static void createCachedThreadPool() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            threadPool.submit(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName() + " is loop of " + i
                                + " , for task of " + taskId);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                }
            });
        }
        System.out.println("all of 10 tasks have committed");
        threadPool.shutdown();
    }

    /**
     * 创建单个线程池，线程可死后复生，保证有一个活着的线程
     */
    public static void createSingleThreadPool() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            threadPool.submit(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName() + " is loop of " + i
                                + " , for task of " + taskId);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                }
            });
        }
        System.out.println("all of 10 tasks have committed");
        threadPool.shutdown();
    }

}
