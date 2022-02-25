package com.xxx.search;

import java.util.ArrayList;

/**
 * 二分查找
 * 二分查找的前提是 数组是有序的
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};

//        int resIndex = binarySearch(arr, 0, arr.length - 1, 1);
//        System.out.println("resIndex = " + resIndex);

        ArrayList<Integer> resIndex2 = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndex = " + resIndex2);
    }

    /**
     * 二分查找法
     *
     * @param arr     数组
     * @param left    左索引
     * @param right   右索引
     * @param findVal 查找的值
     * @return 如果找到就返回下标，没找到就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        // 当 left > right 时，说明递归整个数组但没有找到
        if (left > right) {
            return -1;
        }

        // 中间下标
        int mid = (left + right) / 2;
        // 中间值
        int midVal = arr[mid];
        // 向右递归
        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 查找多个重复元素
     * 在找到mid索引值不要马上返回
     * 向mid索引值的左边扫描，将满足条件的元素下标放入到集合中ArrayList
     * 向mid索引值的右边扫描，将满足条件的元素下标放入到集合中ArrayList
     * 将ArrayList返回
     *
     * @param arr     数组
     * @param left    左索引
     * @param right   右索引
     * @param findVal 查找的值
     */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        // 当 left > right 时，说明递归整个数组但没有找到
        if (left > right) {
            return new ArrayList<>();
        }

        // 中间下标
        int mid = (left + right) / 2;
        // 中间值
        int midVal = arr[mid];
        // 向右递归
        if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<>();
            // 向mid索引值的左边扫描，将满足条件的下标加入到集合ArrayList
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findVal) {
                resIndexList.add(temp);
                // 向左移动
                temp -= 1;
            }
            // 放入中间值
            resIndexList.add(mid);

            // 向mid索引值的右边扫描，将满足条件的下标加入到集合ArrayList
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == findVal) {
                resIndexList.add(temp);
                // 向右移动
                temp += 1;
            }

            return resIndexList;
        }
    }
}
