package aqs;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author minzhang
 * @date 2021/06/06 23:41
 **/
public class AqsTest {



    private static Mlock mlock = new Mlock();

    private static ReentrantLock reentrantLockock = new ReentrantLock();


    // 公平锁
    private static Lock fairLock = new MyReentrantLock(true);

    // 非公平锁
    private static Lock unFairLock = new MyReentrantLock(false);





    @Test
    public void test1(){

        new Thread(()->{
            mlock.lock();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("aaa");
            mlock.unlock();
        },"线程aaa").start();

        new Thread(()->{
            mlock.lock();
            System.out.println("BBB");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mlock.unlock();
        },"线程bbb").start();

        new Thread(()->{
            mlock.lock();
            System.out.println("CCC");
            mlock.unlock();
        },"线程ccc").start();

    }

    @Test
    public void test2(){
        reentrantLockock.lock();
        try {
            // doSomething

        } finally {
            reentrantLockock.unlock();
        }
    }



    @Test
    public void testFairLock0() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        Thread thread = new Thread(
                new MyTask(fairLock));



        for (int i = 0; i < 10; i++) {
            executorService.submit(thread);
        }
    }


    @Test
    public void testFairLock() throws InterruptedException {

        System.out.println("公平锁");
        for (int i = 0; i < 6; i++) {

            Thread thread = new Thread(
                    new MyTask(fairLock));
            thread.setName("线程"+i);
            thread.start();

        }

        Thread.sleep(3000000);
    }


    @Test
    public void testUnFairLock() throws InterruptedException {
        System.out.println("非公平锁");
        for (int i = 0; i < 6; i++) {

            Thread thread = new Thread(
                    new MyTask(unFairLock));
            thread.setName("线程"+i);
            thread.start();

        }
        Thread.sleep(60000);
    }



    private static class MyTask implements Runnable {
        private Lock lock;

        public MyTask(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 2; i++){
                //System.out.println("MyTask"+i);
                try {
                    lock.lock();
                    Thread.sleep(2000);
                    System.out.println("获取锁的当前线程[" + Thread.currentThread().getName() + "], " +
                            "同步队列中的线程" + ((MyReentrantLock)lock).getQueuedThreads() + "");

                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println(e);
                }finally{
                    lock.unlock();
                }
            }
        }
    }

    private static class MyReentrantLock extends ReentrantLock {

        /**
         * Creates an instance of {@code ReentrantLock} with the
         * given fairness policy.
         *
         * @param fair {@code true} if this lock should use a fair ordering policy
         */
        public MyReentrantLock(boolean fair) {
            super(fair);
        }

        @Override
        protected Collection<Thread> getQueuedThreads() {
            //return sync.getQueuedThreads();
            List<Thread> arrayList = new ArrayList<Thread>(super.getQueuedThreads());
            //Collections.reverse(arrayList);
            return arrayList;
        }

    }



}
