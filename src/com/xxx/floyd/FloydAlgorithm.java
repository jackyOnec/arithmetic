package com.xxx.floyd;

import java.util.Arrays;

/**
 * 弗洛伊德
 *
 * @author xxx
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        // 创建连接矩阵
        int[][] matrix = {
                /*A*B*C*D*E*F*G*/
                /*A*/ {0, 5, 7, N, N, N, 2},
                /*B*/ {5, 0, N, 9, N, N, 3},
                /*C*/ {7, N, 0, N, 8, N, N},
                /*D*/ {N, 9, N, 0, N, 4, N},
                /*E*/ {N, N, 8, N, 0, 5, 4},
                /*F*/ {N, N, N, 4, 5, 0, 6},
                /*G*/ {2, 3, N, N, 4, 6, 0}
        };

        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show(vertex);
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

        // 对pre数组初始化,注意存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    /**
     * 显示pre数组和dis数组
     */
    public void show(char[] vertex) {
        for (int k = 0; k < dis.length; k++) {
            // 先将pre数组输出一行
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            // 输出dis数组的一行数据
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[k] + "到" + vertex[i] + "最短路径是" + dis[k][i] + " ");
            }
            System.out.println();
        }
    }

    public void floyd() {
        // 变量保存距离
        int len = 0;
        // 对中间节点的遍历,k就是中间顶点的下标
        for (int k = 0; k < dis.length; k++) {
            // 从i顶点开始出发['A', 'B', 'C']
            for (int i = 0; i < dis.length; i++) {
                // 到达
                for (int j = 0; j < dis.length; j++) {
                    // 求出从i顶点出发,经过k中间顶点,到达j顶点距离
                    len = dis[i][k] + dis[k][j];
                    // 如果len小于dis[i][j]
                    if (len < dis[i][j]) {
                        // 更新距离
                        dis[i][j] = len;
                        // 更新前驱顶点
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}