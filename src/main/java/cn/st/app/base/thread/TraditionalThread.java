package cn.st.app.base.thread;

/**
 * <p>
 * desciption:多线程的实现方式
 * </p>
 * 
 * @author coolearth
 * @date 2015年8月31日
 */
public class TraditionalThread {
    public static void main(String[] args) {
        // extendsThread();
        // implementsRunnable();
        complex();
    }

    /**
     * 继承thread,重写run方法
     */
    public static void extendsThread() {
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("1--current thread name is " + this.getName());
                    System.out.println("1--current thread name is "
                            + Thread.currentThread().getName());
                }
            };
        }.start();
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("2--current thread name is "
                            + Thread.currentThread().getName());
                }
            };
        }.start();
    }

    /**
     * 实现runnable接口，重写run方法
     */
    public static void implementsRunnable() {
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
                    System.out.println("1--current thread name is "
                            + Thread.currentThread().getName());
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
                    System.out.println("2--current thread name is "
                            + Thread.currentThread().getName());
                }
            }
        }).start();
    }

    /**
     * 以下写法的输出结果 thread name is Thread-0 原因 子类重写了父类的方法，不会再调用父类的 if (target != null) { target.run();
     * }代码块
     */
    public static void complex() {
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
                    System.out.println("runnable thread name is "
                            + Thread.currentThread().getName());
                }
            }
        }) {
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
                    System.out.println("thread name is " + Thread.currentThread().getName());
                }
            }
        }.start();
    }
}
