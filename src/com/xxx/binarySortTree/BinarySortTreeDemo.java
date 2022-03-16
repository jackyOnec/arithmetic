package com.xxx.binarySortTree;

/**
 * 二叉排序树
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        // 中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();
    }
}

/**
 * 创建二叉排序树
 */
class BinarySortTree {
    private Node root;

    /**
     * 添加节点的方法
     *
     * @param node 节点
     */
    public void add(Node node) {
        if (root == null) {
            // 如果root为空则直接让root指向node
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
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

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
