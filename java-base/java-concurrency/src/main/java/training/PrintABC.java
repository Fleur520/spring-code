package training;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * 迅雷笔试题）：编写一个程序，开启3个线程，这3个线程的ID分别为A、B、C，每个线程将自己的ID在屏幕上打印10遍，要求输出结果必须按ABC的顺序显示；如：ABCABC….依次递推。
 *
 * @author minzhang
 * @date 2021/05/28 22:30
 **/
public class PrintABC {

    private static final int COUNT = 10;



    /**
     * 使用ReentrantLock实现,充分利用这个锁的特点----它的条件变量（休息室）可以有多个
     */
    @Test
    public  void methodTwo() {

        AwaitSignal awaitSignal = new AwaitSignal(10);
        // 创建三个休息室
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();

        // 启动3个线程
        new Thread(() -> {
            awaitSignal.methodTwoPrint("A", a, b);
        }).start();
        new Thread(() -> {
            awaitSignal.methodTwoPrint("B", b, c);
        }).start();
        new Thread(() -> {
            awaitSignal.methodTwoPrint("C", c, a);
        }).start();

        // 由于这三个线程一启动就进入了休息室等待，因此需要一个线程xian唤醒a休息室中的线程
        try {
            TimeUnit.SECONDS.sleep(1);
            awaitSignal.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            a.signal();
        } finally {
            awaitSignal.unlock();
        }

    }

    static Thread methodThreeA;
    static Thread methodThreeB;
    static Thread methodThreeC;

    /**
     * 使用park()和unpark()实现
     */
    @Test
    public  void methodThree() {

        ParkUnparkSupport parkUnparkSupport = new ParkUnparkSupport(10);

        methodThreeA = new Thread(() -> {
            parkUnparkSupport.print("A", methodThreeB);
        });

        methodThreeB = new Thread(() -> {
            parkUnparkSupport.print("B", methodThreeC);
        });

        methodThreeC = new Thread(() -> {
            parkUnparkSupport.print("C", methodThreeA);
        });
        methodThreeA.start();
        methodThreeB.start();
        methodThreeC.start();
        // 唤醒 a;
        LockSupport.unpark(methodThreeA);

    }

    /**
     * Semaphore 信号量方式
     *
     */
    @Test
    public  void methodFour() {
        Semaphore A = new Semaphore(1);
        Semaphore B = new Semaphore(0);
        Semaphore C = new Semaphore(0);

        final int NUM = 10;

        class SemaphoreThreadA extends Thread {

            @Override
            public void run() {

                try {
                    for (int i = 0; i < NUM; i++) {
                        // A获取信号执行 ，A信号减1 ，当A为0时，无法继续获得该信号值
                        A.acquire();
                        System.out.print("A");
                        // B释放信号，B信号量加1（初始为0），此时可以获取B的信号值
                        B.release();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        class SemaphoreThreadB extends Thread {

            @Override
            public void run() {

                try {
                    for (int i = 0; i < NUM; i++) {
                        // B获取信号执行 ，B信号减1 ，当B为0时，无法继续获得该信号值
                        B.acquire();
                        System.out.print("B");
                        // C释放信号，C信号量加1（初始为0），此时可以获取C的信号值
                        C.release();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        class SemaphoreThreadC extends Thread {

            @Override
            public void run() {

                try {
                    for (int i = 0; i < NUM; i++) {
                        // C获取信号执行 ，C信号减1 ，当C为0时，无法继续获得该信号值
                        C.acquire();
                        System.out.print("C");
                        // A释放信号，A信号量加1（初始为0），此时可以获取A的信号值
                        A.release();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        new SemaphoreThreadA().start();
        new SemaphoreThreadB().start();
        new SemaphoreThreadC().start();

    }

    /**
     * 使用队列 + synchronized锁
     */
    @Test
    public  void methodFive() {
        final int NUM = 10;
        Queue wordList = new LinkedList<>();
        Object todoA = new Object();
        Object todoB = new Object();
        Object todoC = new Object();

        for (int i = 0; i < NUM; i++) {
            wordList.add("A");
            wordList.add("B");
            wordList.add("C");
        }

        new Thread(new PrintWord("1", wordList, todoA, todoB)).start();
        new Thread(new PrintWord("1", wordList, todoB, todoC)).start();
        new Thread(new PrintWord("1", wordList, todoC ,todoA)).start();
        synchronized (todoA){
            todoA.notifyAll();
        }

    }


    /**
     * 使用synchronzied双重锁进行控制
     */
    @Test
    public  void methodSix() throws InterruptedException {

        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter pa = new ThreadPrinter("A",c,a);
        ThreadPrinter pb = new ThreadPrinter("B",a,b);
        ThreadPrinter pc = new ThreadPrinter("C",b,c);

        new Thread(pa).start();
        Thread.sleep(1000);
        new Thread(pb).start();
        Thread.sleep(1000);
        new Thread(pc).start();

    }





}

class PrintWord implements Runnable {
    // id
    String id;
    // 队列
    Queue wordList;
    // 当前队列对象
    Object todo;
    // 下一个对象
    Object todoNext;

    public PrintWord(String id, Queue wordList, Object todo, Object todoNext) {
        this.id = id;
        this.wordList = wordList;
        this.todo = todo;
        this.todoNext = todoNext;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            synchronized (todo) {
                todo.wait();
                Object _word = wordList.peek();
                if (_word != null) {
                    //System.out.print(this.id);
                    System.out.print(_word);
                    wordList.remove();
                }
                synchronized (todoNext) {
                    todoNext.notifyAll();
                }
                if (_word == null) {
                    break;
                }

            }

        }

    }
}
/**
 * 使用ReentrantLock实现
 */
class AwaitSignal extends ReentrantLock {

    private int loopNumber;

    AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    /**
     * 打印
     * 
     * @param str
     *            字符串
     * @param current
     *            当前休息室
     * @param next
     *            下一休息室
     */
    public void methodTwoPrint(String str, Condition current, Condition next) {
        for (int i = 0; i < loopNumber; i++) {
            // 继承自ReentrantLock
            lock();
            try {
                // 表示进入休息室可以被唤醒，可以运行它的打印操作
                current.await();
                System.out.print(str);
                // 打印完成，去唤醒下一休息室
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }

        }

    }

}

class ParkUnparkSupport {
    private int loopNumber;

    ParkUnparkSupport(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Thread next) {
        for (int i = 0; i < loopNumber; i++) {
            // 每次进入循环，先让当前线程park
            LockSupport.park();
            System.out.print(str);
            // 唤醒下一个线程
            LockSupport.unpark(next);

        }
    }
}


class ThreadPrinter implements Runnable{

    private String name ;
    private Object prev;
    private Object self;
    public ThreadPrinter(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @SneakyThrows
    @Override
    public void run() {
        int count = 10 ;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);
                    count--;
                    self.notifyAll();
                }
                // 最后一次打印操作
                if (count == 0) {
                    // 通过notify 释放锁
                    prev.notifyAll();

                } else {
                    prev.wait();
                }
            }

        }
    }
}