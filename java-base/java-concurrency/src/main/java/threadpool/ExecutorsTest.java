package threadpool;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author minzhang
 * @date 2022/04/16 12:54
 **/
public class ExecutorsTest {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static Integer count = 1;

    @Test
    public void test1() {

        MyTimereTask myTimereTask = new MyTimereTask();
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);

        try {
            scheduled.scheduleWithFixedDelay(myTimereTask, 0, 1, TimeUnit.SECONDS);
            scheduled.scheduleWithFixedDelay(myTimereTask, 0, 1, TimeUnit.SECONDS);
            scheduled.scheduleWithFixedDelay(myTimereTask, 0, 1, TimeUnit.SECONDS);
            scheduled.scheduleWithFixedDelay(myTimereTask, 0, 1, TimeUnit.SECONDS);
            while (!scheduled.isTerminated()) {
                lock.readLock().lock();
                if (count > 20) {
                    scheduled.shutdown();
                }
                lock.readLock().unlock();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished all threads");

    }

    private class MyTimereTask implements Runnable {
        @Override
        public void run() {
            lock.writeLock().lock();
            System.out.println("第 " + count + " 次执行任务,count=" + count);
            System.out.println(System.currentTimeMillis());
            count++;
            lock.writeLock().unlock();
        }

    }

}
