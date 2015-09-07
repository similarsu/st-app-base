package cn.st.app.base.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * desciption:锁
 * </p>
 * 
 * @author coolearth
 * @date 2015年9月7日
 */
public class LockTest {
    public static void main(String[] args) {
        final Output output = new Output();
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    output.print("maozedong");
                }

            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    output.print("zhouenlai");
                }
            }
        }).start();
    }

    static class Output {
        private Lock lock = new ReentrantLock();

        public void print(String s) {
            int len = s.length();
            lock.lock();
            for (int i = 0; i < len; i++) {
                System.out.print(s.charAt(i));
            }
            System.out.println();
            lock.unlock();
        }
    }
}
