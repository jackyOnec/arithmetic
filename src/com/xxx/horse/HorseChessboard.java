package com.xxx.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 马踏棋盘算法
 *
 * @author xxx
 */
public class HorseChessboard {

    // 棋盘的列数
    private static int X;
    // 棋盘的行数
    private static int Y;
    // 标记棋盘各个位置是否被访问过
    private static boolean[] visited;
    // 使用一个属性,标记是否棋盘的所有位置都被访问
    private static boolean finished;

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        // 初始位置行,从1开始
        int row = 1;
        // 初始位置的列 从1开始
        int column = 1;
        // 创建棋盘
        int[][] chessboard = new int[X][Y];
        // 初始都false
        visited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时 " + (end - start));
        System.out.println("打印棋盘");
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 算法
     *
     * @param chessboard 棋盘
     * @param row        当前行从零开始
     * @param column     当前列,从零开始
     * @param step       第几步,初始从第一步开始
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        // 标记该位置已经访问
        visited[row * X + column] = true;
        // 获取当前位置可以走的下一个位置集合
        ArrayList<Point> next = next(new Point(column, row));
        // 对next进行排序,排序的规则就是对next的所有Point对象的下一步的位置的数目,进行非递减排序
        sort(next);
        // 遍历next
        while (!next.isEmpty()) {
            // 取出下一个可以走的位置
            Point point = next.remove(0);
            // 判断该点是否已经访问过
            if (!visited[point.y * X + point.x]) {
                // 没有访问过
                traversalChessboard(chessboard, point.y, point.x, step + 1);
            }
        }
        // step < X * Y 成立的情况
        // 棋盘到目前位置,仍然没有走完
        // 棋盘处于一个回溯过程
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前位置(Point对象),计算马儿还能走那些位置(Point),并放入到一个集合中(ArrayList),最多8个位置
     *
     * @param curPoint 当前位置
     * @return 下一跳还能走那些位置
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> points = new ArrayList<>();
        // 创建一个point
        Point point = new Point();
        // 5
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0) {
            points.add(new Point(point));
        }
        // 6
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0) {
            points.add(new Point(point));
        }
        // 7
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0) {
            points.add(new Point(point));
        }
        // 0
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0) {
            points.add(new Point(point));
        }
        // 1
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y) {
            points.add(new Point(point));
        }
        // 2
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y) {
            points.add(new Point(point));
        }
        // 3
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y) {
            points.add(new Point(point));
        }
        // 4
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y) {
            points.add(new Point(point));
        }

        return points;
    }

    /**
     * 根据当前这步的下一步所有选择位置,进行非递减排序,减少回溯的次数
     *
     * @param points 集合
     */
    public static void sort(ArrayList<Point> points) {
//        points.sort((o1, o2) -> {
//            // 获取到o1的下一步的所有位置个数
//            int count1 = next(o1).size();
//            // 获取到o2的下一步的所有位置
//            int count2 = next(o2).size();
//
//            return Integer.compare(count1, count2);
//        });

        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 获取到o1的下一步的所有位置个数
                int count1 = next(o1).size();
//            // 获取到o2的下一步的所有位置
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
