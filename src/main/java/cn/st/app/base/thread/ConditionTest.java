package cn.st.app.base.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * desciption:线程通信
 * </p>
 * 
 * @author coolearth
 * @date 2015年9月14日
 */
public class ConditionTest {
    public static void main(String[] args) {
        // method1();
        method2();
    }

    public static void method1() {
        final Bussiness bussiness = new Bussiness();
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int i = 1; i <= 50; i++) {
                    bussiness.sub(i);
                }
            }
        }).start();
        for (int i = 1; i <= 50; i++) {
            bussiness.main(i);
        }
    }

    public static void method2() {
        final Bussiness3 bussiness3 = new Bussiness3();
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int i = 1; i <= 50; i++) {
                    bussiness3.sub1(i);
                }
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int i = 1; i <= 50; i++) {
                    bussiness3.sub2(i);
                }
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int i = 1; i <= 50; i++) {
                    bussiness3.sub3(i);
                }
            }
        }).start();
    }

    // 子线程循环10次，接着主线程循环100，接着又回到子线程循环10次，接着再回到主线程又循环100，如此循环50次
    static class Bussiness {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        boolean shouldSub = true;

        public void main(int i) {
            lock.lock();
            try {
                while (shouldSub) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 100; j++) {
                    System.out.println("main the sequence of " + j + ",the loop of " + i);
                }
                shouldSub = true;
                condition.signal();
            } finally {
                lock.unlock();
            }

        }

        public void sub(int i) {
            lock.lock();
            try {
                while (!shouldSub) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    System.out.println("sub the sequence of " + j + ",the loop of " + i);
                }
                shouldSub = false;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
    // 线程1循环10,线程2循环100,线程3循环20次,然后又是线程1,接着线程2...一直轮循50次
    static class Bussiness3 {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        int shouldSub = 1;

        public void sub1(int i) {
            lock.lock();
            try {
                while (shouldSub != 1) {
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    System.out.println("sub1 the sequence of " + j + ",the loop of " + i);
                }
                shouldSub = 2;
                condition2.signal();
            } finally {
                lock.unlock();
            }

        }

        public void sub2(int i) {
            lock.lock();
            try {
                while (shouldSub != 2) {
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 100; j++) {
                    System.out.println("sub2 the sequence of " + j + ",the loop of " + i);
                }
                shouldSub = 3;
                condition3.signal();
            } finally {
                lock.unlock();
            }

        }

        public void sub3(int i) {
            lock.lock();
            try {
                while (shouldSub != 3) {
                    try {
                        condition3.await();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 20; j++) {
                    System.out.println("sub3 the sequence of " + j + ",the loop of " + i);
                }
                shouldSub = 1;
                condition1.signal();
            } finally {
                lock.unlock();
            }

        }

    }
}
