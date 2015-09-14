package cn.st.app.base.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * desciption:数据交换
 * </p>
 * 
 * @author coolearth
 * @date 2015年9月14日
 */
public class ExchangerTest {
    /**
     * 模拟一手交钱，一手交货
     * 
     * @param args
     */
    public static void main(String[] args) {
        final Exchanger<Object> exchanger = new Exchanger<Object>();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    String data1 = "drug";
                    System.out.println(Thread.currentThread().getName() + "正在把数据" + data1 + "换出去");
                    Thread.sleep((long) (Math.random() * 10000));
                    String data2 = (String) exchanger.exchange(data1);
                    System.out.println(Thread.currentThread().getName() + "换回数据" + data2);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        service.execute(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    String data1 = "money";
                    System.out.println(Thread.currentThread().getName() + "正在把数据" + data1 + "换出去");
                    Thread.sleep((long) (Math.random() * 10000));
                    String data2 = (String) exchanger.exchange(data1);
                    System.out.println(Thread.currentThread().getName() + "换回数据" + data2);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        service.shutdown();
    }
}
