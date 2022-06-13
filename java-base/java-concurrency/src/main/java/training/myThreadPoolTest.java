package training;


import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author minzhang
 * @date 2022/04/16 22:00
 **/
public class myThreadPoolTest {


    @Test
    public void test1() throws InterruptedException {
        java.util.concurrent.ThreadPoolExecutor threadPool = new java.util.concurrent.ThreadPoolExecutor(
                2, 5, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(30) , Executors.defaultThreadFactory(),
                new java.util.concurrent.ThreadPoolExecutor.AbortPolicy()
        );
        System.out.println("===开始了===");


        for (int i = 0; i < 10; i++) {
            int finalI = i;
            MyThread myThread = new MyThread("A");
            myThread.setName("A"+finalI);
            myThread.setDaemon(true);

            MyThread myThread2 = new MyThread();
            myThread.setName("B"+finalI);

            MyThread myThread3 = new MyThread();
            myThread.setName("C"+finalI);
            threadPool.execute(myThread);
            threadPool.execute(myThread2);
            threadPool.execute(myThread3);

            Thread.sleep(1000);
        }



    }

    public class MyThread extends Thread{
        //  2.创建一个带参数的构造方法，参数传递线程的名称；
        public MyThread(){

        }
        public MyThread(String name){

            super(name);//把线程名称传递给父类，让父类（Thread）给子线程起一个名字
        }
        public void run(){
            //获取线程名称
            System.out.println();
            System.out.println(Thread.currentThread().getName()+"====");
        }
    }




}
