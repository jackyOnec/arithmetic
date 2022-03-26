package com.xxx.prim;

import java.util.Arrays;

/**
 * 普利姆算法
 *
 * @author xxx
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertx = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };

        // 创建MGraph对象
        MGraph graph = new MGraph(vertx);
        // 创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, vertx, data, weight);
        // 输出
        minTree.showGraph(graph);

        minTree.prim(graph, 0);
    }
}

/**
 * 创建最小生成树->村庄图
 */
class MinTree {
    /**
     * 创建图的邻接矩阵
     *
     * @param graph  图对象
     * @param vertx  图对应的顶点个数
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int vertx, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < vertx; i++) {
            // 顶点
            graph.data[i] = data[i];
            for (j = 0; j < vertx; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 显示图的邻接矩阵
     *
     * @param graph 图对象
     */
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写prim算法,得到最小树
     *
     * @param graph 图对象
     * @param v     表示从图的第几个顶点开始生成
     */
    public void prim(MGraph graph, int v) {
        // visited表示标记节点是否被访问过
        int[] visited = new int[graph.vertx];

        // visited默认元素的值都是0，表示没有访问过
        for (int i = 0; i < graph.vertx; i++) {
            visited[i] = 0;
        }

        // 把当前这个节点标记为已访问
        visited[v] = 1;
        // 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        // 将minWeight初始化成一个大数，后面在遍历过程中，会被替换
        int minWeight = 10000;

        for (int k = 1; k < graph.vertx; k++) { // 因为有graph.vertx顶点，普利姆算法后，有graph.vertx-1边
            // 这个是确定每一次生成的子图，和哪个节点的距离最近
            for (int i = 0; i < graph.vertx; i++) {// i节点表示被访问过的节点
                for (int j = 0; j < graph.vertx; j++) {// j节点表示还没有被访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        // 替换minWeight(寻找已经访问的节点和未访问过节点间的权值最小边)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            // 找到一条最小边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + ">权值：" + minWeight);
            // 将当前这个节点标记为已访问
            visited[h2] = 1;
            // minWeight 重新设置为最大值
            minWeight = 10000;
        }
    }
}

/**
 * 图
 */
class MGraph {
    // 表示图节点个数
    int vertx;
    // 存放节点数据
    char[] data;
    // 存放边，邻接矩阵
    int[][] weight;

    public MGraph(int vertx) {
        this.vertx = vertx;
        data = new char[vertx];
        weight = new int[vertx][vertx];
    }
}
