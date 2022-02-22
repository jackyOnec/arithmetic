package com.xxx.sort;

/**
 * 插入排序
 * 保存待插入数据，与下个位置的数据进行比较，
 * 第一个数比第二个数小，那么就将第二个替换第一个数，然后将保存的数据替换到第二个数的位置，依次类推
 * <p>
 * 保存一个数值和下标，然后用保存的数值去和数组中的数值进行比较，每次取一个下标和数值直到比对完成
 */
public class InsertSort {
    public static void main(String[] args) {
        // 创建给8万个数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成一个(0，80000)的随机数字
            arr[i] = (int) (Math.random() * 80000);
        }
        long millis = System.currentTimeMillis();
        System.out.println("millis = " + millis);
        insertSort(arr);
        long timeMillis = System.currentTimeMillis();
        System.out.println("timeMillis = " + timeMillis);
        System.out.println(timeMillis - millis);


//        int[] arr = {101, 34, 119, 1};
//        insertSort(arr);
//        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        /*// 定义待插入的数
        int insertVal = arr[1];
        int insertIndex = 1 - 1;

        // 给insertVal找到插入的位置
        // insertIndex >= 0 保证insertVal 插入不越界
        // insertVal < arr[insertIndex] 待插入的数，还没找到插入位置需要将arr[insertIndex]后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("第一轮插入");
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        System.out.println("第二轮插入");
        insertVal = arr[2];
        insertIndex = 2 - 1;

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        System.out.println("第三轮插入");
        insertVal = arr[3];
        insertIndex = 3 - 1;

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));*/
    }

    /**
     * 插入排序
     *
     * @param arr 数组
     */
    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            // 定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1;

            // 给insertVal找到插入的位置
            // insertIndex >= 0 保证insertVal 插入不越界
            // insertVal < arr[insertIndex] 待插入的数，还没找到插入位置需要将arr[insertIndex]后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                // 往前移
                arr[insertIndex + 1] = arr[insertIndex];
                // 往后比对
                insertIndex--;
            }
            // 退出循环说明找到插入的位置，insertIndex + 1
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
//            System.out.println("第" + i + "轮插入");
//            System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        }
    }
}
