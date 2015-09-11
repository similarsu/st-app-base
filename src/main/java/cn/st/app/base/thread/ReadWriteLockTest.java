package cn.st.app.base.thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 * desciption:读写锁 ：多个读锁不互斥，读锁与写锁互斥，写锁与写锁互斥
 * </p>
 * 
 * @author coolearth
 * @date 2015年9月7日
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        final Queue3 q3 = new Queue3();
        for (int i = 0; i < 3; i++) {
            new Thread() {
                public void run() {
                    while (true) {
                        q3.get();
                    }
                }

            }.start();

            new Thread() {
                public void run() {
                    while (true) {
                        q3.put(new Random().nextInt(10000));
                    }
                }

            }.start();
        }

    }

    static class Queue3 {
        private Object data = null;
        private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void get() {
            readWriteLock.readLock().lock();

            try {
                System.out.println(Thread.currentThread().getName() + "准备读取数据");
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println(Thread.currentThread().getName() + "读取数据：" + data);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
            }


        }

        public void put(Object data) {
            readWriteLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "准备放入数据");
                Thread.sleep((long) (Math.random() * 1000));
                this.data = data;
                System.out.println(Thread.currentThread().getName() + "放入数据：" + data);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            readWriteLock.writeLock().unlock();
        }
    }
}
