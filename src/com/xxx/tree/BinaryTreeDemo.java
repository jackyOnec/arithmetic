package com.xxx.tree;

/**
 * 二叉树
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建二叉树
        BinaryTree binaryTree = new BinaryTree();

        // 创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode root2 = new HeroNode(2, "吴用");
        HeroNode root3 = new HeroNode(3, "卢俊义");
        HeroNode root4 = new HeroNode(4, "林冲");
        HeroNode root5 = new HeroNode(5, "关胜");

        // 手动创建二叉树
        root.setLeft(root2);
        root.setRight(root3);
        root3.setRight(root4);
        root3.setLeft(root5);
        binaryTree.setRoot(root);

        System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        binaryTree.postOrder();

        System.out.println("前序遍历查找");
        int no = 2;
        // 前序遍历次数：4
        HeroNode resNode = binaryTree.preOrderSearch(no);
        if (resNode != null) {
            System.out.println(resNode);
//            System.out.printf("no = %d name = %s", resNode.getNo(), resNode.getName());
        } else {
            System.out.println("没有找到no为" + no + "的信息");
        }
        System.out.println("中序遍历查找");
        // 中序遍历次数：3
        resNode = binaryTree.infixOrderSearch(no);
        if (resNode != null) {
            System.out.println(resNode);
//            System.out.printf("no = %d name = %s", resNode.getNo(), resNode.getName());
        } else {
            System.out.println("没有找到no为" + no + "的信息");
        }

        System.out.println("后序遍历查找");
        // 后序遍历次数：2
        resNode = binaryTree.postOrderSearch(no);
        if (resNode != null) {
            System.out.println(resNode);
//            System.out.printf("no = %d name = %s", resNode.getNo(), resNode.getName());
        } else {
            System.out.println("没有找到no为" + no + "的信息");
        }
    }
}

/**
 * 定义BinaryTree二叉树
 */
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 前序遍历查找
     *
     * @param no 编号
     * @return 找到返回node, 没找到返回null
     */
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 中序遍历查找
     *
     * @param no 编号
     * @return 找到返回node, 没找到返回null
     */
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 后序遍历查找
     *
     * @param no 编号
     * @return 找到返回node, 没找到返回null
     */
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

/**
 * HeroNode节点
 */
class HeroNode {
    private int no;
    private String name;
    // 左节点
    private HeroNode left;
    // 右节点
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历的方法
     */
    public void preOrder() {
        // 先输出父节点
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 先输出父节点
        System.out.println(this);
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        // 先输出父节点
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no 编号
     * @return 找到返回node, 没找到返回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("前序");
        // 比较当前节点是不是等于要查找的编号
        if (this.no == no) {
            return this;
        }
        // 判断当前节点的左节点是否为空，不为空，则递归前序查找
        // 左递归前序查找，找到节点则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        // 左节树找到了就返回
        if (resNode != null) {
            return resNode;
        }

        // 当前节点的右子节点是否为空，不为空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     *
     * @param no 编号
     * @return 找到返回node, 没找到返回null
     */
    public HeroNode infixOrderSearch(int no) {
        // 判断当前节点的左节点是否为空，不为空，则递归中序查找
        // 左递归中序查找，找到节点则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        // 左节树找到了就返回
        if (resNode != null) {
            return resNode;
        }
        System.out.println("中序");
        // 比较当前节点是不是等于要查找的编号
        if (this.no == no) {
            return this;
        }
        // 当前节点的右子节点是否为空，不为空，则继续向右递归中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历查找
     *
     * @param no 编号
     * @return 找到返回node, 没找到返回null
     */
    public HeroNode postOrderSearch(int no) {
        // 判断当前节点的左节点是否为空，不为空，则递归后序查找
        // 左递归中序查找，找到节点则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        // 左节树找到了就返回
        if (resNode != null) {
            return resNode;
        }
        // 左节树没有找到 右子节点是否为空，不为空，则继续向右递归后序查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        // 右节树找到了就返回
        if (resNode != null) {
            return resNode;
        }
        System.out.println("后序");
        // 比较当前节点是不是等于要查找的编号
        if (this.no == no) {
            return this;
        }
        return null;
    }
}
