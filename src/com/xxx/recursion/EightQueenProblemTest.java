package com.xxx.recursion;

/**
 * 八皇后问题
 * 皇后可以在横、竖、斜线上不限步数地吃掉其他棋子
 * 用一维数组解决
 * array[]下标表示第几行，即第几个皇后，array[i] = val，val表示第i+1个皇后，放在第i+1行的第val+1列
 * 用到了斜率公式
 */
public class EightQueenProblemTest {
    // 统计次数
    static int cont = 0;
    // 统计judgeCount 次数
    static int judgeCount = 0;
    //定义一个max表示共有多少个皇后
    int max = 8;
    // 定义数组array，保存皇后放置位置的结果，比如 array = {0， 4， 7， 5，2， 6， 1， 3}
    int[] array = new int[max];

    public static void main(String[] args) {
        EightQueenProblemTest eightQueenProblemTest = new EightQueenProblemTest();
        eightQueenProblemTest.check(0);
        System.out.println("共" + cont + "种解法");
        // 判断冲突次数
        System.out.println("judgeCount = " + judgeCount);
    }

    /**
     * 放置皇后
     * check 是每一次递归时进入到check中都有for循环，因此产生回溯
     *
     * @param n 第几个皇后，从0开始
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
            // 行不变，列加1
        }
    }

    /**
     * 查看当我们放置第几个皇后，就去检查该皇后是否和前面已经摆放的皇后冲突
     *
     * @param n 第几个皇后
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // Math.abs(x) 函数返回指定数字 “x“ 的绝对值
            // array[i] == array[n] 是否在同一列
            // Math.abs(n - i) == Math.abs(array[n] - array[i]) 是否在同一斜线
            /*
                判断两个点是否在同一斜线上
                规律：一条斜线中任意取两个棋格，该棋格的行索引减去另一个棋格的行索引得到的绝对值
                一定等于该棋格的列索引减去另一个棋格的列索引得到的绝对值
                斜率k的公式
                1.斜率亦称“角系数”，表示平面直角坐标系中表示一条直线对横坐标轴的倾斜程度的量。
                2.斜率用来量度斜坡的斜度。在数学上，直线的斜率处处相等，它是直线的倾斜程度的量度。
                3.运用微积分可计算出曲线中的任一点的斜率。
                斜率等1就是斜线
                (y2 - y1)/(x2 - x1) = 1
                当行差等于列差的时候，就代表两个皇后在同一斜线上。
             * 例如第二个皇后 n = 1 ，i = 0
             * 放置到第二列第二行 array[1] = 1
             * 其实就是 行-行 == 列-列
             * Math.abs(1 - 0) == 1 列
             * Math.abs(array[n] - array[i]) = Math.abs(1 - 0) = 1 行
             * 横的值相减=竖的值，那就是斜率为1，在斜线上
             * 判断是否在同一行，因为n每次都在递增的所以不用判断
             */
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
