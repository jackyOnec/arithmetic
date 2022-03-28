package com.xxx.dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉
 *
 * @author xxx
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 邻接矩阵
        // 65535表示不能连接
        final int N = 65535;
        int[][] matrix = {
                /* *//*A*B*C*D*E*F*G*/
                /*A*/ {N, 5, 7, N, N, N, 2},
                /*B*/ {5, N, N, 9, N, N, 3},
                /*C*/ {7, N, N, N, 8, N, N},
                /*D*/ {N, 9, N, N, N, 4, N},
                /*E*/ {N, N, 8, N, N, 5, 4},
                /*F*/ {N, N, N, 4, 5, N, 6},
                /*G*/ {2, 3, N, N, 4, 6, N}
        };
        // 创建Graph对象
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();

    }


}

/**
 * 已访问顶点集合
 */
class VisitedVertex {
    // 记录各个顶点是否访问过 1表示访问过，0未访问，会动态更新
    public int[] already_arr;
    // 每个下标对应的值为前一个顶点下标，会动态更新
    public int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其它顶点的距离，会动态更新，求最短距离会存放到dis
    public int[] dis;
}


/**
 * 图
 */
class Graph {
    // 顶点数组
    private char[] vertex;
    // 邻接矩阵
    private int[][] matrix;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    /**
     * 显示图
     */
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }
}