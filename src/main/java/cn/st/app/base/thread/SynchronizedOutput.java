package cn.st.app.base.thread;

/**
 * <p>
 * desciption:
 * </p>
 * 
 * @author coolearth
 * @date 2015年8月31日
 */
public class SynchronizedOutput {
    public void print(String name) {
        int len = name.length();
        synchronized (SynchronizedOutput.class) {
            for (int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

    }

    public synchronized void print2(String name) {
        int len = name.length();
        for (int i = 0; i < len; i++) {
            System.out.print(name.charAt(i));
        }
        System.out.println();

    }

    public synchronized static void print3(String name) {
        int len = name.length();
        for (int i = 0; i < len; i++) {
            System.out.print(name.charAt(i));
        }
        System.out.println();
    }
}
