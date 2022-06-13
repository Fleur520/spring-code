package training;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author minzhang
 * @date 2022/04/23 21:58
 **/
public class PrintAbcTest {

    private static final int COUNT = 10;

    @Test
    public void test1(){

        char[] one = "123456".toCharArray();
        char[] A = "abcdef".toCharArray();


        TransferQueue<Character> queue = new LinkedTransferQueue<Character>();


        new Thread(()->{
            for(char c : one){
                try {
                    System.out.println(queue.take());
                    queue.transfer(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();


        new Thread(()->{
            for(char c : A){
                try {
                    queue.transfer(c);
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();
    }



    /**
     *  使用thread join
     */
    @Test
    public  void methodSeven() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Thread threadA = new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.print("A");
            }
        };
        Thread threadB = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                threadA.join();
                System.out.print("B");
            }
        };
        Thread threadC = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                threadB.join();
                System.out.print("C");
            }
        };

        threadA.start();
        threadB.start();
        threadC.start();

        for (int i = 0; i < 10; i++) {
            executorService.submit(threadA);
            executorService.submit(threadB);
            executorService.submit(threadC);
        }
        executorService.shutdown();
    }


    /**
     * 使用固定容量线程数为一的线程池
     */
    @Test
    public  void methodOne() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Thread threadA = new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.print("A");
            }
        };
        Thread threadB = new Thread() {
            @Override
            public void run() {
                System.out.print("B");
            }
        };
        Thread threadC = new Thread() {
            @Override
            public void run() {
                System.out.print("C");
            }
        };

        for (int i = 0; i < COUNT; i++) {
            executorService.submit(threadA);
            executorService.submit(threadB);
            executorService.submit(threadC);
        }
        executorService.shutdown();
    }




}
