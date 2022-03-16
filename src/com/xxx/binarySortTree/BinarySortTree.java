package com.xxx.binarySortTree;

/**
 * 二叉排序树
 */
public class BinarySortTree {
    public static void main(String[] args) {

    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 添加节点的方法
     * 递归的形式添加节点，注意需要满足二叉排序树的要求
     *
     * @param node 节点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        // 判断传入节点的值，和当前子树的根节点的值关系
        if (node.value < this.value) {
            // 如果当前节点左子节点为null
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归向左子树添加
                this.left.add(node);
            }
        } else {
            // 添加节点的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                // 递归向右添加
                this.right.add(node);
            }
        }
    }
}
