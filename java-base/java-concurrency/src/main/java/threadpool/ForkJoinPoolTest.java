package threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 *   分而治之思想
 */
public class ForkJoinPoolTest {



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for(int i = 0; i < 10; i++){
            ForkJoinTask forkJoinTask = forkJoinPool.submit(new Fibonacci(i));
            System.out.println(forkJoinTask.get());

        }


    }


}
class Fibonacci extends RecursiveTask<Integer>
{

    int n ;
    public Fibonacci(int n){
        this.n = n;
    }


    @Override
    protected Integer compute() {
        if(n<=1){
            return n;
        }
        Fibonacci f1 =new Fibonacci(n-1);
        Fibonacci f2 = new Fibonacci(n-2);
        f1.fork();
        f2.fork();
        System.out.println( "f1"+ f1.fork());;
        System.out.println( "f2"+ f2.fork() );;
        return f1.join()+f2.join();

    }
}





