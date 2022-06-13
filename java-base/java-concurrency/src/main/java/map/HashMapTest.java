package map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * @author minzhang
 * @date 2022/03/19 17:00
 **/
public class HashMapTest {


    public static Map<Integer, Integer> map = new HashMap<>();
    
    public static ConcurrentHashMap<Integer, Integer> concurrentHashMapap = new ConcurrentHashMap<>();
    
    //private static final AtomicLong ai = new AtomicLong();

    private static AtomicInteger ai = new AtomicInteger();

    

    @Test
    public  void test2() throws InterruptedException {
        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();


        ExecutorService executorService = Executors.newFixedThreadPool(1);
        IntStream.range(0, 1000000).forEach(i -> executorService.submit(() -> {
            try {
                objectObjectConcurrentHashMap.put("con"+ i,i+"====");
                objectObjectHashMap.put("hash"+ i,i+"====");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("ConcurrentHashMap"+objectObjectConcurrentHashMap);
        System.out.println("HashMap=========="+objectObjectHashMap);
        System.out.println(objectObjectHashMap.size()==objectObjectHashMap.size());

    }


    @Test
    public void test3() throws InterruptedException {

       ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 1000000).forEach(i -> executorService.submit(new HashMapThread())
                );
        // executorService.shutdown();
        executorService.awaitTermination(0, TimeUnit.SECONDS);

        System.out.println(concurrentHashMapap);
        System.out.println(concurrentHashMapap.size());

    }
    class HashMapThread extends Thread {
        @Override
        public void run() {
                //ai.incrementAndGet();
                increment();
            }
    }

    public synchronized void increment() {
        ai.incrementAndGet();
        concurrentHashMapap.put(ai.get(), ai.get());
        System.out.println("===concurrentHashMapap==="+ai.get());
    }



}
