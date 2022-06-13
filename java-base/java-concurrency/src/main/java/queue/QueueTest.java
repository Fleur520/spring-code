package queue;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import wiremock.org.apache.commons.lang3.RandomUtils;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author minzhang
 * @date 2022/04/16 21:08
 **/
@Slf4j
public class QueueTest {



    @Test
    public void test1() throws InterruptedException {

        PriorityBlockingQueue<SaleApple> priorityQueue = new PriorityBlockingQueue<>(3);
        Thread t1 = new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                for (int i = 1; i < 11; i++) {
                    SaleApple apple = new SaleApple(RandomUtils.nextInt(), "one_brandNo" + i);
                    boolean offer = priorityQueue.offer(apple);
                    log.info("one_offer:{}, value:{}", offer, apple.price);
                }
            }
        }, "t1");

        t1.start();
        Thread.sleep(3000);
        SaleApple apple = priorityQueue.take();
        log.info(" price:{}, brand:{}", apple.price, apple.brand);


    }

    static class SaleApple implements Comparable<SaleApple> {
        int price; // 苹果的价格
        String brand; // 草果的品种
        SaleApple(int price, String brand){
            this.price = price;
            this.brand = brand;
        }
        @Override
        public int compareTo(SaleApple o) {
            return this.price - ((SaleApple) o).price;
        }
    }


}
