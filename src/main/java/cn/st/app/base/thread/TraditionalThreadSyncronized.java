package cn.st.app.base.thread;

/**
 * <p>
 * desciption: 传统线程同步
 * </p>
 * 
 * @author coolearth
 * @date 2015年8月31日
 */
public class TraditionalThreadSyncronized {
    public static void main(String[] args) {
        // hasProblem();
        // useSynchronized();
        useSynchronizedComplex2();
    }

    /**
     * 一个个输出字符，未考虑同步，出现乱套的问题
     */
    public static void hasProblem() {
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

    public static void useSynchronized() {
        final SynchronizedOutput output = new SynchronizedOutput();
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

    public static void useSynchronizedMethod() {
        final SynchronizedOutput output = new SynchronizedOutput();
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
                    output.print2("maozedong");
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
                    output.print2("zhouenlai");
                }
            }
        }).start();
    }

    public static void useSynchronizedComplex() {
        final SynchronizedOutput output = new SynchronizedOutput();
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
                    output.print2("zhouenlai");
                }
            }
        }).start();
    }


    public static void useSynchronizedComplex2() {
        final SynchronizedOutput output = new SynchronizedOutput();
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
                    output.print3("zhouenlai");
                }
            }
        }).start();
    }
}
