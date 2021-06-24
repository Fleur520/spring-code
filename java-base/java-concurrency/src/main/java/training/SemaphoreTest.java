package training;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import lombok.SneakyThrows;

/**
 * @author minzhang
 * @date 2021/06/06 23:03
 **/
public class SemaphoreTest {


    public static void main(String[] args) {
        // workerAndMachine();
        semUseWithThreadPool();
    }


    /**
     * 一个工厂有5台机器，但是有8个工人，一台机器只能同时被一个工人使用，使用完了其他人才能继续使用
     */
    static void workerAndMachine(){
        int workerNum  = 8;
        int machine = 5;
        Semaphore sem = new Semaphore(machine);

        for (int i = 0; i < workerNum; i++){
            new Workers(i,sem).start();
        }
    }

    static class Workers extends Thread {
        private int num ;
        private Semaphore sem;


        Workers(int num, Semaphore sem) {
            this.num = num;
            this.sem = sem;
        }

        @SneakyThrows
        @Override
        public void run() {
            sem.acquire();
            System.out.println("工人"+this.num+"占用一个机器在生产");
            Thread.sleep(2000);
            System.out.println("工人"+this.num+"释放机器");
            sem.release();


        }
    }


    static Semaphore semaphore = new Semaphore(3);

    /**
     * 建立一个线程池
     * 限制同时并发数量
     * 模拟慢请求
     */
    static void semUseWithThreadPool(){
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for(int i = 0; i < 1000 ;i++){
            executorService.submit(new ThreadInstance());
        }
        executorService.shutdown();

    }

    static class ThreadInstance extends Thread {

        @SneakyThrows
        @Override
        public void run() {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"拿到了许可证");
            Thread.sleep(2000);
            semaphore.release();

        }
    }


}
