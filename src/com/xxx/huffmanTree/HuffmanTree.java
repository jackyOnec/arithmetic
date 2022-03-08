package com.xxx.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 赫夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        System.out.println("huffmanTree = " + huffmanTree);
        preOrder(huffmanTree);
    }

    /**
     * 前序遍历
     *
     * @param root 节点
     */
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树，不能遍历");
        }
    }

    /**
     * 创建霍夫曼树
     *
     * @param arr 需要创建霍夫曼树数组
     * @return 霍夫曼树
     */
    public static Node createHuffmanTree(int[] arr) {
        // 遍历arr数组,将arr的每个元素构成一个Node,将Node放入到ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            // 排序从小到大
            Collections.sort(nodes);
//            System.out.println("nodes = " + nodes);

            // 取出根节点权值最小的两颗二叉树
            // 取出权值最小的节点(二叉树)
            Node leftNode = nodes.get(0);
            // 取出权值次小的节点(二叉树)
            Node rightNode = nodes.get(1);
            // 构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            // 从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将parent加入到nodes
            nodes.add(parent);
            Collections.sort(nodes);
//            System.out.println("nodes = " + nodes);
        }
        return nodes.get(0);
    }
}

/**
 * 创建节点类
 * 为了让Node对象持续排序 Collections集合排序
 * 让Node实现Comparable接口
 */
class Node implements Comparable<Node> {
    // 节点权值
    int value;
    // 指向左子节点
    Node left;
    // 指向右子节点
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.value - o.value;
        // 从大到小排序
//        return -(this.value - o.value);
    }
}
