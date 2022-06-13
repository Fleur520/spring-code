package threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountdownLatch
 *
 * @author minzhang
 * @date 2021/05/28 22:12
 **/
public class CountdownLatchTest {

    private final static int THREAD_NUM =5;

    /**
     * 各个线程执行完成后，主线程做总结性工作的例子
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++){
            executorService.submit(new CountdownLatchTask(countDownLatch,"A"));
            executorService.submit(new CountdownLatchTask(countDownLatch,"B"));
            executorService.submit(new CountdownLatchTask(countDownLatch,"C"));
        }
        countDownLatch.await();
        System.out.println("大家都执行完了 ，做总结工作");

        executorService.shutdown();

    }
    static class CountdownLatchTask implements Runnable {

        private final CountDownLatch lock;

        private final String threadName;

        CountdownLatchTask(CountDownLatch lock, String threadName) {
            this.lock = lock;
            this.threadName = threadName;
        }

        @Override
        public void run() {
            System.out.print(threadName);
            lock.countDown();

        }
    }
}
