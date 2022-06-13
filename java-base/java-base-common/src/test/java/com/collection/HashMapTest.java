package com.collection;

import java.util.HashMap;

import org.junit.Test;

/**
 * @author minzhang
 * @date 2022/02/12 11:52
 **/
public class HashMapTest {


    @Test
    public void test1() {

      HashMap<String, String> map = new HashMap<String, String>(100);
        map.put("aaa","bbb");

        System.out.println("=======");

        System.out.println(map.get("aaa"));

        System.out.println(map);


    }



}
