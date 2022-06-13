import java.util.concurrent.*;

import org.junit.Test;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author minzhang
 * @date 2022/02/03 19:56
 **/
@Slf4j
public class futureTest {



    @Test
    public void jdkTest() throws ExecutionException, InterruptedException {
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 2. 提交任务, callable有返回结果, runnable没有返回结果
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(5000);
                return 50;
            }
        });
        // 阻塞方法 get(), 等到有结果
        System.out.println(future.get());

        System.out.println("取消了吗?:" + future.isCancelled());
        future.cancel(true);// true代表强制cancel
        System.out.println("取消了吗?:" + future.isCancelled());

        System.out.println("完成了嘛?:" + future.isDone());
        System.out.println("线程池结束了吗?:"+executorService.isShutdown());
        executorService.shutdown();
        System.out.println("取消了吗?:" + future.isCancelled());
        System.out.println("线程池结束了吗?:"+executorService.isShutdown());
    }

    @Test
    public void nettyFutreTest() throws InterruptedException, ExecutionException {
        NioEventLoopGroup group = new NioEventLoopGroup();// 会有多个loop(executor)

        // 拿到一个EventLoop
        EventLoop loop = group.next();

        Future<Integer> future = loop.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("正在运行...");
                Thread.sleep(1000);
                return 70;
            }
        });
        log.debug("等待结果");
        //Thread.sleep(10000);
        log.debug("结果===now" + ((io.netty.util.concurrent.Future<Integer>) future).getNow());
        log.debug("结果===" + future.get());

        group.shutdownGracefully();
    }

    @Test
    public void promiseTest() throws InterruptedException {

        // Promise 相当于一个结果容器
        DefaultPromise<Object> promise = new DefaultPromise<>(new NioEventLoopGroup().next());
        // 主动创建Promise对象
        new Thread(() -> {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 向Promise容器填充对象
            promise.setSuccess(80);
        }).start();

        System.out.println(promise.getNow());
        Thread.sleep(1100);
        System.out.println(promise.getNow());
    }



}
