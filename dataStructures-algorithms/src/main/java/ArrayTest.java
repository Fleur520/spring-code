import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author minzhang
 * @date 2021/06/20 16:36
 **/
public class ArrayTest {


    public static void main(String[] args) {
        // deleteArray();
        removeSamelementE();
    }


    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，
     * 返回移除后的数组和新的长度，你不需要考虑数组中超出新长度后面的元素。
     * 要求，空间复杂度为 O(1)，即不要使用额外的数组空间。
     */
    static void removeSamelementE() {
        int nums[] = {1, 3, 22, 4, 4, 5};

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {

            if (set.contains(nums[i])) {
                // 把最后一个元素替代指定的元素
                nums[i] = nums[nums.length - 1];
                // 数组缩容
                nums = Arrays.copyOf(nums, nums.length - 1);
            } else {
                System.out.println(nums[i]);
                set.add(nums[i]);
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    static void deleteArray() {
        // 删除数组中的某一个元素的方法：

        // 把最后一个元素替代指定的元素，然后数组缩容

        Scanner sc = new Scanner(System.in);

        int[] arr = new int[] {1, 2, 4, 5, 9, 8, 0};

        System.out.println(Arrays.toString(arr));

        System.out.println("请输入要删除第几个元素：");

        int n = sc.nextInt();
        sc.close();

        // 把最后一个元素替代指定的元素

        arr[n - 1] = arr[arr.length - 1];

        // 数组缩容
        arr = Arrays.copyOf(arr, arr.length - 1);

        System.out.println(Arrays.toString(arr));

    }



}
