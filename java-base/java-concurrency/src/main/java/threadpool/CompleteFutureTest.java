package threadpool;

import java.util.concurrent.*;

/**
 * @author minzhang
 * @date 2021/12/19 14:57
 **/
public class CompleteFutureTest {


    static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            CompletableFuture cf = CompletableFuture.completedFuture("").
                    thenRun(new Thread(() -> System.out.print("A"))).
                    thenRun(new Thread(() -> System.out.print("B"))).
                    thenRun(new Thread(() -> System.out.print("C")));
        }
    }



}
