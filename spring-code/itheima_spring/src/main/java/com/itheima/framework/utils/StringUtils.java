package com.itheima.framework.utils;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

/**
 * @version v1.0
 * @ClassName: StringUtils
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 黑马程序员
 */
public class StringUtils {
    private StringUtils() {

    }

    // userDao   ==>   setUserDao
    public static String getSetterMethodByFieldName(String fieldName) {
        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        return methodName;
    }


    public static void main(String[] args) {

        int[] numbers = new int[]{ 3,2,4 };
        twoSum(numbers,6);
    }


    public static int[] twoSum (int[] numbers, int target) {
        // write code here
        int size = numbers.length;
        System.out.println(numbers[0]);
        System.out.println(numbers[1]);
        System.out.println(numbers[2]);
        int[] res = new int [2] ;
        int i = 0;
        int j = 0;
        for(i = 0 ;i <size ;i++){
            for( j=i+1 ;j<size; j++){
                if (numbers[i] + numbers[j]==target){
                    System.out.println(i+"=="+numbers[i]);
                    System.out.println(j+"=="+numbers[j]);
                    res[0]=i+1;
                    res[1]=j+1;
                    Arrays.sort(res);
                    return res;
                }
            }

        }
        return res;
    }


}
