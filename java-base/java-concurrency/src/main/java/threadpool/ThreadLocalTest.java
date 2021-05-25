package threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 测试
 *
 * @author minzhang
 * @date 2021/05/25 23:02
 **/
public class ThreadLocalTest {


    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finali =i ;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalTest().date(finali);
                    System.out.println(date);
                }
            });
        }
        threadPool.shutdown();

    }

    public String date(int seconds){
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatLocal.get();
        return dateFormat.format(date);

    };
}
class  ThreadSafeFormatter{
    public static ThreadLocal<SimpleDateFormat> dateFormatLocal = new ThreadLocal<SimpleDateFormat>() {

        // threadLocal的initialValue
        @Override
        protected SimpleDateFormat initialValue(){
            return new SimpleDateFormat("mm:ss");
        }



    };

}
