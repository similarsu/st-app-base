package cn.st.app.base.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 * desciption:线程范围内共享变量
 * </p>
 * 
 * @author coolearth
 * @date 2015年9月6日
 */
public class ThreadScopeShareData {


    public static void main(String[] args) {
        // method1();
        // method2();
        // method3();
        // method4();
        method5();

    }

    private static int data = 0;

    // 使用static
    public static void method1() {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    data = new Random().nextInt();
                    System.out
                            .println(Thread.currentThread().getName() + " has put data : " + data);
                    new A().getData();
                    new B().getData();
                }
            }).start();
        }
    }

    // 使用hashmap
    public static void method2() {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    int data = new Random().nextInt();
                    System.out
                            .println(Thread.currentThread().getName() + " has put data : " + data);
                    threadData.put(Thread.currentThread(), data);
                    new A1().getData();
                    new B1().getData();
                }
            }).start();
        }
    }

    // 使用threadlocal
    public static void method3() {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    int data = new Random().nextInt();
                    System.out
                            .println(Thread.currentThread().getName() + " has put data : " + data);
                    threadLocal.set(data);
                    new A2().getData();
                    new B2().getData();
                }
            }).start();
        }
    }

    // 共享多个变量，将多个变量封装成对象
    public static void method4() {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    int data = new Random().nextInt();
                    System.out
                            .println(Thread.currentThread().getName() + " has put data : " + data);
                    MyThreadScopeData myData = new MyThreadScopeData();
                    myData.setName("name" + data);
                    myData.setAge(data);
                    myThreadLocal.set(myData);
                    new A3().getData();
                    new B3().getData();
                }
            }).start();
        }
    }

    // 优雅的实现方式，将threadLocal隐瞒
    public static void method5() {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    int data = new Random().nextInt();
                    System.out
                            .println(Thread.currentThread().getName() + " has put data : " + data);
                    MyThreadScopeDataUpgrade myData = MyThreadScopeDataUpgrade.getInstance();
                    myData.setName("name" + data);
                    myData.setAge(data);
                    new A4().getData();
                    new B4().getData();
                }
            }).start();
        }
    }

    static class A {

        public void getData() {
            System.out.println("A get the data from " + Thread.currentThread().getName() + " : "
                    + data);
        }
    }


    static class B {

        public void getData() {
            System.out.println("B get the data from " + Thread.currentThread().getName() + " : "
                    + data);
        }
    }

    private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();

    static class A1 {

        public void getData() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("A get the data from " + Thread.currentThread().getName() + " : "
                    + data);
        }
    }


    static class B1 {

        public void getData() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("B get the data from " + Thread.currentThread().getName() + " : "
                    + data);
        }
    }

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

    static class A2 {

        public void getData() {
            int data = threadLocal.get();
            System.out.println("A get the data from " + Thread.currentThread().getName() + " : "
                    + data);
        }
    }


    static class B2 {

        public void getData() {
            int data = threadLocal.get();
            System.out.println("B get the data from " + Thread.currentThread().getName() + " : "
                    + data);
        }
    }


    private static ThreadLocal<MyThreadScopeData> myThreadLocal =
            new ThreadLocal<MyThreadScopeData>();

    static class A3 {

        public void getData() {
            MyThreadScopeData myData = myThreadLocal.get();
            System.out.println("A from " + Thread.currentThread().getName() + " getMyData :"
                    + myData.getName() + "," + myData.getAge());
        }
    }


    static class B3 {

        public void getData() {
            MyThreadScopeData myData = myThreadLocal.get();
            System.out.println("A from " + Thread.currentThread().getName() + " getMyData :"
                    + myData.getName() + "," + myData.getAge());
        }
    }

    static class A4 {

        public void getData() {
            MyThreadScopeDataUpgrade myData = MyThreadScopeDataUpgrade.getInstance();
            System.out.println("A from " + Thread.currentThread().getName() + " getMyData :"
                    + myData.getName() + "," + myData.getAge());
        }
    }


    static class B4 {

        public void getData() {
            MyThreadScopeDataUpgrade myData = MyThreadScopeDataUpgrade.getInstance();
            System.out.println("A from " + Thread.currentThread().getName() + " getMyData :"
                    + myData.getName() + "," + myData.getAge());
        }
    }
}


class MyThreadScopeData {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}


class MyThreadScopeDataUpgrade {

    private MyThreadScopeDataUpgrade() {

    }


    private static final ThreadLocal<MyThreadScopeDataUpgrade> map =
            new ThreadLocal<MyThreadScopeDataUpgrade>();

    public static MyThreadScopeDataUpgrade getInstance() {
        MyThreadScopeDataUpgrade instance = map.get();
        if (instance == null) {
            instance = new MyThreadScopeDataUpgrade();
            map.set(instance);
        }
        return instance;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
