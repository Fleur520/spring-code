package com.jvm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author minzhang
 * @date 2022/03/28 12:40
 **/
public class JvmTest {




    @Test
    public void test() throws InterruptedException {
        System.out.println("AAA");

        // 100kb
        byte[] a= new byte[1024*100];
        ArrayList<JvmTest> objects = new ArrayList<>();
        while (true){
            objects.add(new JvmTest());
            Thread.sleep(1);


        }



    }



}
