package map;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author minzhang
 * @date 2022/04/17 12:25
 **/
public class HashsetTest {


    @Test
    public void test1(){

        HashSet<String> strings = new HashSet<>();

        strings.add("aaa");
        strings.add("ccc");
        strings.add("ddd");
        strings.add("bbb");

        System.out.println(strings);

    }

    @Test
    public void test2() {
        Set<String> strings = new TreeSet<>();
        strings.add("aaa");
        strings.add("ccc");
        strings.add("ddd");
        strings.add("bbb");

        System.out.println(strings);
    }

    @Test
    public void test3() {
        Set<String> strings = new LinkedHashSet<>();
        strings.add("aaa");
        System.out.println(strings);
        strings.add("ccc");
        System.out.println(strings);
        strings.add("ddd");
        System.out.println(strings);
        strings.add("bbb");

        System.out.println(strings);
    }



}
