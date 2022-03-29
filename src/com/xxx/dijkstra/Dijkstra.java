package com.xxx.dijkstra;

/**
 * @author xxx
 */
public class Dijkstra {
    // 代表正无穷
    public static final int M = 10000;
    public static String[] names = new String[]{"A", "B", "C", "D", "E", "F", "G",};

    public static void main(String[] args) {
        // 二维数组每一行分别是 A、B、C、D、E 各点到其余点的距离,
        // A -> A 距离为0, 常量M 为正无穷
        int[][] weight1 = {
                {0, 12, M, M, M, 16, 14},
                {12, 0, 10, M, M, 7, M},
                {M, 10, 0, 3, 5, 6, M},
                {M, M, 3, 0, 4, M, M},
                {M, M, 5, 4, 0, 2, 8},
                {16, 7, 6, M, 2, 0, 9},
                {14, M, M, M, 8, 9, 0}
        };

        int start = 0;
        int[] shortPath = dijkstra(weight1, start);
        System.out.println("===============");
        for (int i = 0; i < shortPath.length; i++) {
            System.out.println("从" + names[start] + "出发到" + names[i] + "的最短距离为：" + shortPath[i]);
        }
    }

    /**
     * Dijkstra算法
     *
     * @param weight 图的权重矩阵
     * @param start  起点编号start（从0编号，顶点存在数组中）
     * @return 返回一个int[] 数组，表示从start到它的最短路径长度
     */
    public static int[] dijkstra(int[][] weight, int start) {
        // 顶点个数
        int n = weight.length;
        // 标记当前该顶点的最短路径是否已经求出,1表示已求出
        int[] visited = new int[n];
        // 保存start到其他各点的最短路径
        int[] shortPath = new int[n];

        // 保存start到其他各点最短路径的字符串表示
        String[] path = new String[n];
        for (int i = 0; i < n; i++) {
            path[i] = names[start] + "-->" + names[i];
        }

        // 初始化，第一个顶点已经求出
        shortPath[start] = 0;
        visited[start] = 1;

        // 要加入n-1个顶点
        for (int count = 1; count < n; count++) {
            // 选出一个距离初始顶点start最近的未标记顶点
            int k = -1;
            int dMin = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0 && weight[start][i] < dMin) {
                    dMin = weight[start][i];
                    k = i;
                }
            }

            // 将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
            shortPath[k] = dMin;
            visited[k] = 1;

            // 以k为中间点，修正从start到未访问各点的距离
            for (int i = 0; i < n; i++) {
                // 如果 '起始点到当前点距离' + '当前点到某点距离' < '起始点到某点距离', 则更新
                if (visited[i] == 0 && weight[start][k] + weight[k][i] < weight[start][i]) {
                    weight[start][i] = weight[start][k] + weight[k][i];
                    path[i] = path[k] + "-->" + names[i];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println("从" + names[start] + "出发到" + names[i] + "的最短路径为：" + path[i]);
        }

        return shortPath;
    }
}

