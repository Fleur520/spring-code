package threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

/**
 * Test
 *
 * @author minzhang
 * @date 2021/05/28 22:00
 **/
public class ThreadPoolTest {

    @Test
    public void test(){

        Thread thraed  =new Thread(){
            @Override
            public void run() {
                System.out.println("thread");
                super.run();
            }
        };
        thraed.start();
    }
    @Test
    public void test2() throws Exception {

        Callable callable = new Callable() {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public Object call() throws Exception {
                System.out.println("callable thread ");
                return null;
            }
        };
        final Object call = callable.call();
    }

    @Test
    public void test3() throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable thread ");
            }
        };
        runnable.run();

    }




}
