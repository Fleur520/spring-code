package threadpool;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * ThreadLocal 测试
 *
 * @author minzhang
 * @date 2021/05/25 23:02
 **/
public class ThreadLocalTest {


    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);

    public static void main(String[] args) {
        for (int i = 0; i < 70; i++) {
            int finali =i ;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalTest().date(finali);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();

    }

    public String date(int seconds){
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatLocal.get();
        return dateFormat.format(date);
    };
}
class  ThreadSafeFormatter{
    public static ThreadLocal<SimpleDateFormat> dateFormatLocal = new ThreadLocal<SimpleDateFormat>() {

        // threadLocal的initialValue
        @Override
        protected SimpleDateFormat initialValue(){
            return new SimpleDateFormat("mm:ss");
        }

    };

    @Test
    public void test2() throws InterruptedException {
        System.out.println("=====xxxx=====");

        ThreadLocal<String> threadLocal1 = new ThreadLocal<String>() ;
        ThreadLocal<String> threadLocal2 = new ThreadLocal<String>() ;
        threadLocal1.set("aaa");
        threadLocal2.set("bbb");
        String main1 = threadLocal1.get();
        String main2 = threadLocal2.get();
        System.out.println("thread main==" + main1);
        System.out.println("thread main==" + main2);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        IntStream.range(0, 5).forEach(i -> executorService.submit(() -> {
            try {

                String s = threadLocal1.get();
                System.out.println("thread =="+s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }

    @Test
    public void test3() throws InterruptedException {
        //创建线程本地变量
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();

        //在main线程中添加main线程的本地变量
        threadLocal.set("mainVal");
        //新创建一个子线程
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("子线程中的本地变量值:"+threadLocal.get());
            }
        });
        thread.start();
        //输出main线程中的本地变量值
        System.out.println("mainx线程中的本地变量值:"+threadLocal.get());
    }


    @Test
    public void test4(){

        //获取当前线程的group
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        //在当前线程执行流中新建一个Group1
        ThreadGroup group1 = new ThreadGroup("Group1");

        //Group1的父线程,就是main线程所在Group
        System.out.println(group1.getParent() == currentGroup);
        //定义Group2, 指定group1为其父线程
        ThreadGroup group2 = new ThreadGroup(group1, "Group2");
        System.out.println(group2.getParent() == group1);

    }

    @Test
    public void test5() throws InterruptedException {
        //创建线程本地变量
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();

        //在main线程中添加main线程的本地变量
        threadLocal.set("mainVal");
        //新创建一个子线程
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("子线程中的本地变量值:"+threadLocal.get());
            }
        });
        thread.start();
        //输出main线程中的本地变量值
        System.out.println("mainx线程中的本地变量值:"+threadLocal.get());
    }







}
