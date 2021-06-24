package training;

import java.util.concurrent.CyclicBarrier;

import lombok.SneakyThrows;

/**
 * @author minzhang
 * @date 2021/06/08 21:55
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier =new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("凑齐了三人 ，出发");
            }
        });

        for (int i = 0; i < 10; i++) {
            new Thread(new Task(i+1 ,cyclicBarrier)).start();
        }



    }

    /**
     *  公园游玩，三人成行
     *  初始人数100人
     */
    static class Task implements Runnable {

        private int id ;

        private CyclicBarrier cyclicBarrier;

        Task(int id, CyclicBarrier cyclicBarrier) {
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
