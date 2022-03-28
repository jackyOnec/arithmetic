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
        graph.dsj(6);
        // 显示结果
        graph.showDijkstra();
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

    /**
     * @param length 顶点个数
     * @param index  出发顶点对应的下标
     */
    public VisitedVertex(int length, int index) {
        already_arr = new int[length];
        pre_visited = new int[length];
        dis = new int[length];

        //初始化数组
        Arrays.fill(dis, 65535);
        // 设置出发顶点被访问过为1
        already_arr[index] = 1;
        // 设置出发顶点的访问距离为零
        dis[index] = 0;
    }

    /**
     * 判断index顶点是否被访问过
     *
     * @param index 顶点
     * @return 访问过返回true，否则返回false
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     *
     * @param index 顶点
     * @param len   距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新顶点前驱顶点为index顶点
     *
     * @param pre   前驱顶点
     * @param index 顶点
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     *
     * @param index 顶点
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择并返回新访问顶点
     *
     * @return
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        // 更新index顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    /**
     * 显示结果
     */
    public void show() {
        System.out.println("===============================");
        System.out.println("输出already_arr");
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("输出pre_visited");
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("输出dis");
        for (int di : dis) {
            System.out.print(di + " ");
        }
        System.out.println();
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "(" + i + ")");
            } else {
                System.out.println("N ");
            }
            count++;
        }
    }
}


/**
 * 图
 */
class Graph {
    // 顶点数组
    private char[] vertex;
    // 邻接矩阵
    private int[][] matrix;
    // 已经访问的顶点集合
    private VisitedVertex visitedVertex;

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

    /**
     * 显示结果
     */
    public void showDijkstra() {
        visitedVertex.show();
    }

    /**
     * 迪杰斯特拉算法
     *
     * @param index 出发顶点对应的下标
     */
    public void dsj(int index) {
        visitedVertex = new VisitedVertex(vertex.length, index);

        // 更新index下标到周围顶点的距离和前驱顶点
        update(index);

        for (int j = 1; j < vertex.length; j++) {
            // 选择并返回新访问顶点
            index = visitedVertex.updateArr();
            // 更新index顶点到周围顶点的距离和前驱顶点
            update(index);
        }
    }

    /**
     * 更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
     *
     * @param index
     */
    private void update(int index) {
        int len = 0;
        // 根据遍历我们的邻接矩阵matrix[index]
        for (int j = 0; j < matrix[index].length; j++) {
            // 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
            len = visitedVertex.getDis(index) + matrix[index][j];

            // j顶点没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
            if (!visitedVertex.in(j) && len < visitedVertex.getDis(j)) {
                // 更新j顶点的前驱为index顶点
                visitedVertex.updatePre(j, index);
                // 更新出发顶点到j顶点的距离
                visitedVertex.updateDis(j, len);
            }
        }
    }
}