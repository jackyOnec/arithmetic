package com.xxx.recursion;

/**
 * 八皇后问题
 * 用一维数组解决
 * array[]下标表示第几行，即第几个皇后，array[i] = val，val表示第i+1个皇后，放在第i+1行的第val+1列
 */
public class EightQueenProblemTest {
    // 统计次数
    static int cont = 0;
    //定义一个max表示共有多少个皇后
    int max = 8;
    // 定义数组array，保存皇后放置位置的结果，比如 array = {0， 4， 7， 5，2， 6， 1， 3}
    int[] array = new int[max];

    public static void main(String[] args) {
        EightQueenProblemTest eightQueenProblemTest = new EightQueenProblemTest();
        eightQueenProblemTest.check(0);
        System.out.println("共" + cont + "种解法");
    }

    /**
     * 放置皇后
     * check 是每一次递归时进入到check中都有for循环，因此产生回溯
     *
     * @param n 第几个皇后
     */
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前这个皇后n, 放到该行的第一列
            array[n] = i;
            // 判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) {
                // 接着放置n+1个皇后，开始递归
                check(n + 1);
            }
            // 如果冲突就继续执行array[n] = i; 即将第n个皇后放置在行的后一个位置
        }
    }

    /**
     * 查看当我们放置第几个皇后，就去检查该皇后是否和前面已经摆放的皇后冲突
     *
     * @param n 第几个皇后
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // Math.abs(x) 函数返回指定数字 “x“ 的绝对值
            // array[i] == array[n] 是否在同一列
            // Math.abs(n - i) == Math.abs(array[n] - array[i]) 是否在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将皇后摆放的位置输出
     */
    private void print() {
        cont++;
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
