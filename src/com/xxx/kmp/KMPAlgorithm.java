package com.xxx.kmp;

import java.util.Arrays;

/**
 * KPM算法
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBCABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);
        System.out.println("next = " + Arrays.toString(next));

        int search = kmpSearch(str1, str2, next);
        System.out.println("search = " + search);

        int kmp = kmp(str1, str2);
        System.out.println("kmp = " + kmp);
    }

    /**
     * @param str1 源字符串
     * @param str2 子串
     * @return 返回第一个匹配的位置，没有返回-1
     */
    public static int kmp(String str1, String str2) {
        if (str2.length() > str1.length() || str1 == null || str2 == null) {
            return -1;
        }
        int[] next = kmpNext(str2);
        return kmpSearch(str1, str2, next);
    }

    /**
     * kmp搜索算法
     *
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表，子串对应的部分匹配
     * @return 返回第一个匹配的位置，没有返回-1
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        // 遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            // 需要处理str1.charAt(i) != str2.charAt(j)，去调整j的大小
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            // 找到了
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * @param dest 子串
     * @return 获取到一个字符串(子串)的部分匹配值表
     */
    public static int[] kmpNext(String dest) {
        // 创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;// 如果字符串是长度为1，部分匹配值就是0

        // i表示字符串后缀 j表示字符串前缀
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当不成立时，需要从next[j-1]获取新的j
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            // 满足时，部分匹配值就+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
