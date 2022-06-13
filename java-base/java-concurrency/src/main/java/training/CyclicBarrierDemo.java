package training;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import lombok.SneakyThrows;

/**
 * @author minzhang
 * @date 2021/06/08 21:55
 **/
public class CyclicBarrierDemo {


    static CyclicBarrier barrier1 = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
        }
    });
    static CyclicBarrier barrier2 = new CyclicBarrier(2);

    static class PrintThreadA extends Thread
    {

        @Override
        public void run() {
            System.out.print("A");
            Thread.currentThread().setName("A");
            //放开栅栏1
            try {
                barrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static class PrintThreadB extends Thread
    {
        @Override
        public void run() {
            Thread.currentThread().setName("B");
            try {
                barrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.print("B");
            //放开栅栏1
            try {
                barrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


    static class PrintThreadC extends Thread
    {
        @Override
        public void run() {
            Thread.currentThread().setName("C");
            try {
                barrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.print("C");
        }
    }

    public static void main(String[] args) {


        for (int i = 0; i < 1; i++) {
            new PrintThreadA().start();
            new PrintThreadB().start();
            new PrintThreadC().start();
        }
    }


    /**
     *  公园游玩，三人成行
     *  初始人数100人
     */
    static class TaskPrintAbc implements Runnable {

        private String id ;

        private CyclicBarrier cyclicBarrier;

        TaskPrintAbc(String id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }


        @SneakyThrows
        @Override
        public void run() {
            System.out.println("同学"+id +"大门出发，前往驿站");
            System.out.println(id);
            Thread.sleep(3000);
            System.out.println("同学"+id +"到了驿站");
            cyclicBarrier.await();
            System.out.println("同学"+id + "开始骑车");

        }
    }



    /**
     *  公园游玩，三人成行
     *  初始人数100人
     */
    static class Task implements Runnable {

        private String id ;

        private CyclicBarrier cyclicBarrier;

        Task(String id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }


        @SneakyThrows
        @Override
        public void run() {
            System.out.println("同学"+id +"大门出发，前往驿站");
            Thread.sleep(3000);
            System.out.println("同学"+id +"到了驿站");
            cyclicBarrier.await();
            System.out.println("同学"+id + "开始骑车");

        }
    }



}
