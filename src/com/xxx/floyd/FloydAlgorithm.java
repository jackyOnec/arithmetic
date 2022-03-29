package com.xxx.floyd;

/**
 * 弗洛伊德
 *
 * @author xxx
 */
public class FloydAlgorithm {
    public static void main(String[] args) {

    }
}

/**
 * 图
 */
class Graph {
    // 存放顶点的数组
    private char[] vertex;
    // 保存从各个顶点出发到其他顶点的距离，最后结果，也是保留在该数组
    private int[][] dis;
    // 保存到达目标顶点的前驱顶点
    private int[][] pre;

    /**
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
    }
}