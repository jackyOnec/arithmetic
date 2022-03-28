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