package com.xxx.search;

import java.util.HashMap;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class singleNumber {
    public static void main(String[] args) {
//        int[] arr = {2, 2, 1};
        int[] arr = {4, 1, 2, 1, 2, 8, 8};
//        int uniqueNum = uniqueNum(arr);
//        System.out.println("uniqueNum = " + uniqueNum);

        int test = test(arr);
        System.out.println("test = " + test);
    }

    /**
     * 位异或：第一个操作数的第n位于第二个操作数的第n位 相反，那么结果的第n为也为1，否则为0
     * 二进制
     *
     * @param arr 数组
     * @return 只出现了一次的元素
     */
    public static int uniqueNum(int[] arr) {
        int result = 0;
        for (int j : arr) {
            result = result ^ j;
        }
        return result;
    }

    /**
     * 另一种解法
     *
     * @param arr 数组
     * @return 只出现了一次的元素
     */
    public static int test(int[] arr) {
        // 统计数组出现的次数
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int j : arr) {
            hashMap.merge(j, 1, Integer::sum);
        }
//        System.out.println("hashMap = " + hashMap);
        for (Integer i : hashMap.keySet()) {
            if (hashMap.get(i) == 1) {
                return i;
            }
        }
        return 0;
    }
}
