package cn.st.app.base.thread;

/**
 * <p>
 * desciption: 线程间通信<br/>
 * problem:子线程执行10次，主线程执行100次,子线程再执行10次，主线程再执行100次，如此循环50次
 * </p>
 * 
 * @author coolearth
 * @date 2015年9月1日
 */
public class TraditionalThreadCommunication {
    public static void main(String[] args) {
        // method1();
        method2();
    }

    public static void method1() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int j = 0; j < 50; j++) {
                    synchronized (TraditionalThreadCommunication.class) {
                        for (int i = 0; i < 10; i++) {
                            System.out.println("sub thread sequence of " + i + ", loop of " + j);
                        }
                    }

                }

            }
        }).start();
        for (int j = 0; j < 50; j++) {
            synchronized (TraditionalThreadCommunication.class) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("main thread sequence of " + i + ", loop of " + j);
                }
            }
        }
    }

    public static void method2() {
        final Business business = new Business();
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int j = 0; j < 50; j++) {
                    business.sub(j);
                }

            }
        }).start();
        for (int j = 0; j < 50; j++) {
            business.main(j);
        }
    }
}


class Business {
    private boolean shouldSub = true;

    public synchronized void sub(int j) {
        while (!shouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("sub thread sequence of " + i + ", loop of " + j);
        }
        shouldSub = false;
        this.notify();
    }

    public synchronized void main(int j) {
        while (shouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 100; i++) {
            System.out.println("main thread sequence of " + i + ", loop of " + j);
        }
        shouldSub = true;
        this.notify();
    }
}
