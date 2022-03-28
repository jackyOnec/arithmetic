package com.xxx.kruskal;

import java.util.Arrays;

/**
 * 克鲁斯卡尔
 *
 * @author xxx
 */
public class KruskalCase {
    // 表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;
    // 边的个数
    private int edgeNum;
    // 顶点数组
    private char[] vertx;
    // 邻接矩阵
    private int[][] matrix;

    /**
     * 构造器
     *
     * @param vertx  顶点数组
     * @param matrix 邻接矩阵
     */
    public KruskalCase(char[] vertx, int[][] matrix) {
        //  初始化顶点数和边的个数
        int vertxLen = vertx.length;

        // this.X=X会指向同地址，用this.X=X.clone()是新地址，数组是传址的
//        this.vertx = vertx.clone();
        // 初始化顶点，复制拷贝
        this.vertx = new char[vertxLen];
        // 复制数组
        System.arraycopy(vertx, 0, this.vertx, 0, vertx.length);

        // 初始化边，用复制拷贝方式
        this.matrix = new int[vertxLen][vertxLen];
        for (int i = 0; i < vertxLen; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, vertxLen);
        }

        // 统计边的条数
        for (int i = 0; i < vertxLen; i++) {
            for (int j = i + 1; j < vertxLen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public static void main(String[] args) {
        char[] vertx = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}
        };
        KruskalCase kruskalCase = new KruskalCase(vertx, matrix);
        kruskalCase.print();
        EData[] edgers = kruskalCase.getEdgers();
        kruskalCase.sortEdges(edgers);
        System.out.println("edgers = " + Arrays.toString(edgers));
        kruskalCase.kruskal();
    }

    /**
     * 打印邻接矩阵
     */
    public void print() {
        System.out.println("邻接矩阵为:");
        for (int i = 0; i < vertx.length; i++) {
            for (int j = 0; j < vertx.length; j++) {
//                System.out.printf("%d\t", matrix[i][j]);
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边进行排序处理，冒泡排序
     *
     * @param eData 边的集合
     */
    private void sortEdges(EData[] eData) {
        for (int i = 0; i < eData.length - 1; i++) {
            for (int j = 0; j < eData.length - 1 - i; j++) {
                if (eData[j].weight > eData[j + 1].weight) {// 交换
                    EData tmp = eData[j];
                    eData[j] = eData[j + 1];
                    eData[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * @param ch 顶点的值，比如'A','B'
     * @return 返回ch顶点对应的下标，如果找不到返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertx.length; i++) {
            if (vertx[i] == ch) { // 找到
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中边，放到EData[]数组中，需要遍历该数组
     * 通过matrix邻接矩阵来获取的
     *
     * @return 图中边
     */
    private EData[] getEdgers() {
        int index = 0;
        EData[] eData = new EData[edgeNum];
        for (int i = 0; i < vertx.length; i++) {
            for (int j = i + 1; j < vertx.length; j++) {
                if (matrix[i][j] != INF) {
                    eData[index++] = new EData(vertx[i], vertx[j], matrix[i][j]);
                }
            }
        }
        return eData;
    }

    /**
     * 获取下标为i的顶点的终点, 用于判断两个顶点的终点是否相同
     * 算法核心点
     *
     * @param connections 连接点的集合 数组记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中，逐步形成的
     * @param i           下标  传入顶点对应的下标
     * @return 返回的就是，下标为i的这个顶点对应的终点下标
     */
    private int getEnd(int[] connections, int i) {
        while (connections[i] != 0) {
            i = connections[i];
        }
        return i;
    }

    /**
     *
     */
    public void kruskal() {
        // 最后结果数组的索引
        int index = 0;
        // 用于保存“已有最小生成数”中的每个顶点在最小生成树中的终点
        int[] ends = new int[edgeNum];
        // 创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        // 获取图中所有边集合，一共12条边
        EData[] edgers = getEdgers();
//        System.out.println("图边的集合：" + Arrays.toString(edgers) + " 共" + edgers.length);
        // 按照边的权值从小到大排序
        sortEdges(edgers);

        // 遍历edges数组，将边添加到最小生成树中，判断是准备加入的边是否形成回路，没有就加入rests，否则不加入
        for (int i = 0; i < edgeNum; i++) {
            // 获取到第i条边的第一个顶点
            int position1 = getPosition(edgers[i].start);
            // 获取到第i条边的第二个顶点
            int position2 = getPosition(edgers[i].end);

            // 获取p1这个顶点在已有最小生成树中的终点
            int end1 = getEnd(ends, position1);
            // 获取p2顶点在已有最小生成树中的终点
            int end2 = getEnd(ends, position2);
            // 和判断是否构成回路
            if (end1 != end2) {
                // 没构成回路，则设置 end1 在“已有最小生成树”中的终点
                ends[end1] = end2;
                // 有一条边加入到rets数组
                rets[index++] = edgers[i];
            }
        }
        // 统计并打印“最小生成树”，输出rets
        System.out.println("最小生成树为");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }
}

/**
 * 创建一个类EData,它的对象实例就是表示一条边
 */
class EData {
    // 边的一个点
    char start;
    // 边的另一个点
    char end;
    // 边的权值
    int weight;

    // 构造器
    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}