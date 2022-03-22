package com.xxx.Hanoi;

/**
 * 汉诺塔
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔的移动方法
     * 使用分治算法
     *
     * @param num 盘
     * @param a   A柱
     * @param b   B柱
     * @param c   C柱
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只要一个盘
        if (num == 1) {
            System.out.println("第1个盘" + a + "->" + c);
        } else {
            // 如果有n>=2情况，我们总是可以看做是两个盘1最下边的一个盘2上面的所有盘
            // 先把最上面的所有盘A->B，移动过程会所有到c
            hanoiTower(num - 1, a, c, b);
            // 把最下边的盘A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            // 把B塔的所有盘从B->C，移动过程使用到A塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
