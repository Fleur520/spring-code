package training;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author minzhang
 * @date 2021/06/08 21:20
 **/
public class ConditionDemo {

    private ReentrantLock lock =new ReentrantLock();

    private Condition condition = lock.newCondition();


    void method1() throws InterruptedException {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "条件不满足，开始等待");
            condition.await();
            System.out.println(Thread.currentThread().getName() + "条件满足了，开始执行");
        }finally {
            lock.unlock();
        }
    }

    void method2(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"需要等待5秒钟时间");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+"准备工作完成，唤醒其他线程");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo conditionDemo = new ConditionDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                conditionDemo.method2();
            }
        }).start();
        //Thread.sleep(3000);

        conditionDemo.method1();
    }
}
