package aqs;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author minzhang
 * @date 2022/02/19 19:30
 **/
public class Mlock {



    private static final Unsafe unsafe = reflectGetUnsafe();

    private volatile int state;

    private static long stateOffset;


    static {
        try {
            stateOffset = unsafe.objectFieldOffset(
                    Mlock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

     public  void lock(){

         System.out.println(Thread.currentThread().getName()+"进来了");
         // 判断当前线程要不要阻塞
         while(!unsafe.compareAndSwapInt(this,stateOffset,0,1)){
             System.out.println(Thread.currentThread().getName()+"正在加锁");
         }


     }


     public void unlock(){
            System.out.println(Thread.currentThread().getName()+"解锁");
            state = 0 ;
     }

    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
