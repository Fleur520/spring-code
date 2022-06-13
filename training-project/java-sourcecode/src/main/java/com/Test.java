package com;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author minzhang
 * @date 2021/12/18 19:45
 **/
public class Test {

    private static ConcurrentLinkedQueue<Integer> QUEUE;


    private static ConcurrentHashMap<Integer, String> SLOTS;

    public static void main(String[] args) {
        SLOTS = new ConcurrentHashMap<>();
        int currentIndexMaxValue = 1024;
        QUEUE = IntStream.range(0, currentIndexMaxValue).boxed().collect(Collectors.toCollection(ConcurrentLinkedQueue::new));
        Integer poll = QUEUE.poll();

        for (int i = 0; i < 100; i++) {
            poll = QUEUE.poll();
            SLOTS.put(poll,"name"+poll);
            System.out.println(poll);
        }

        SLOTS.remove(60);
        SLOTS.remove(60);
        SLOTS.remove(99);

        for (int i = 0; i < 100; i++) {
            SLOTS.get(i);
            System.out.println(SLOTS.get(i));
        }

    }
}
