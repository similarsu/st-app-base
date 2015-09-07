package cn.st.app.base.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * <p>
 * desciption:
 * </p>
 * 
 * @author coolearth
 * @date 2015年9月7日
 */
public class CallableAndFuture {
    public static void main(String[] args) {
        // method1();
        // method2();
        method3();
    }

    /**
     * 获取结果
     */
    public static void method1() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // TODO Auto-generated method stub
                Thread.sleep(2000);
                return "hello";
            }
        });
        System.out.println("等待结果");
        try {
            System.out.println("拿到结果：" + future.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 设置超时时间
     */
    public static void method2() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // TODO Auto-generated method stub
                Thread.sleep(2000);
                return "hello";
            }
        });
        System.out.println("等待结果");
        try {
            System.out.println("拿到结果：" + future.get(1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 同时提交多个任务，谁先完成，谁先输出
     */
    public static void method3() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService =
                new ExecutorCompletionService<Integer>(threadPool);
        for (int i = 0; i < 10; i++) {
            final int seq = i;
            completionService.submit(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    // TODO Auto-generated method stub
                    Thread.sleep(new Random().nextInt(5000));
                    return seq;
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
