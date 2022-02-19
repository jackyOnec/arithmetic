package com.xxx.recursion;

/**
 * 迷宫
 */
public class MazeTest {
    public static void main(String[] args) {
        // 创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        // 1 表示墙
        // 上下设置为墙
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[map.length - 1][i] = 1;
        }
        // 左右设置为墙
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][map[0].length - 1] = 1;
        }
        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("地图情况");
        for (int[] ints : map) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        // 使用递归回溯找路
        setWay(map, 1, 1);

        System.out.println("输出新的地图，小球走过，并标识过递归");
        for (int[] ints : map) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 递归回溯来给小球找路
     * i和j从地图的那个位置开始出发（1，1）
     * 如果小球能到map[6][5]位置，则说明通路
     * 约定当map[i][j]为0表示该点没有走过 当为1表示墙，2表示通路可以走，3表示该店已经走过但是不通
     * 在走迷宫时，需要确定一个策略（方法）下->右->上->左 如果该点不通再回溯
     *
     * @param map 地图
     * @param i   从那个位置开始找
     * @param j   从那个位置开始找
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 道路引进找到
            return true;
        } else {
            // 如果当前这个点还没走过
            if (map[i][j] == 0) {
                // 按照策略 下->右->上->左 走
                map[i][j] = 2;
                // 向下
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {// 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {// 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {// 向左走
                    return true;
                } else {
                    // 说明该店走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果map[i][j] != 0,可能是1, 2, 3
                return false;
            }
        }
    }
}
