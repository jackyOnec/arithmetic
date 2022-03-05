package com.xxx.tree;

/**
 * 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode1 root = new HeroNode1(1, "tom");
        HeroNode1 root2 = new HeroNode1(3, "jack");
        HeroNode1 root3 = new HeroNode1(6, "smith");
        HeroNode1 root4 = new HeroNode1(8, "mary");
        HeroNode1 root5 = new HeroNode1(10, "king");
        HeroNode1 root6 = new HeroNode1(14, "dim");

        root.setLeft(root2);
        root.setRight(root3);
        root2.setLeft(root4);
        root2.setRight(root5);
        root3.setLeft(root6);

        threadedBinaryTree threadedBinaryTree = new threadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        // 以10号节点测试
        HeroNode1 leftNode = root5.getLeft();
        HeroNode1 rightNode = root5.getRight();
        System.out.println(root5.getNo() + "号节点前驱节点为" + leftNode);
        System.out.println(root5.getNo() + "号节点后继节点为" + rightNode);

        System.out.println("遍历线索化二叉树");
        threadedBinaryTree.threadedList();
    }
}

/**
 * 定义BinaryTree二叉树
 */
class threadedBinaryTree {
    private HeroNode1 root;

    // 创建一个指向前驱节点的指针来实现线索化 在递归进行线索化时 pre 总是保留当前一个节点
    private HeroNode1 pre = null;

    public void setRoot(HeroNode1 root) {
        this.root = root;
    }

    /**
     * 重载threadedNodes方法
     */
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * 遍历线索化二叉树
     */
    public void threadedList() {
        // 定义一个变量，存储当前遍历的节点，从root开始
        HeroNode1 node = this.root;

        while (node != null) {
            /*
             * 循环找到leftType == 1 的节点，第一个找到加上 8 节点
             * 后面随着遍历而变化，当leftType == 1 时，说明该节点是按照线索化处理后的有效节点
             */
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            // 打印当前这个节点
            System.out.println(node);
            // 当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                // 获取当前节点后继节点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历节点
            node = node.getRight();
        }
    }

    /**
     * 二叉树进行中序线索化
     *
     * @param node1 需要线索化的节点
     */
    public void threadedNodes(HeroNode1 node1) {
        // 节点为空，不能线索化
        if (node1 == null) {
            return;
        }
        // 线索化左子树
        threadedNodes(node1.getLeft());

        // 线索化当前节点
        // 处理当前节点的前驱节点
        /*
         * 以 8 节点来理解
         * 8 节点的left = null， leftType = 1
         */
        if (node1.getLeft() == null) {
            // 让当前节点的左指针指向前驱节点
            node1.setLeft(pre);
            // 修改当前节点的左指针的类型，指向前驱节点
            node1.setLeftType(1);
        }
        // 处理后继节点
        if (pre != null && pre.getRight() == null) {
            // 让前驱节点的指针指向当前节点
            pre.setRight(node1);
            // 修改前驱节点的右指针类型
            pre.setRightType(1);
        }

        // 每处理一个节点后，让当前节点指向下一个节点的前驱节点
        pre = node1;

        // 线索化右子树
        threadedNodes(node1.getRight());
    }

    /**
     * 删除节点
     *
     * @param no 编号
     */
    public void delNode(int no) {
        if (root != null) {
            // 如果只有一个root节点，这里立即判断root是不是需要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除");
        }
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
    public HeroNode1 preOrderSearch(int no) {
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
    public HeroNode1 infixOrderSearch(int no) {
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
    public HeroNode1 postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

class HeroNode1 {
    private int no;
    private String name;
    // 左节点
    private HeroNode1 left;
    // 右节点
    private HeroNode1 right;

    // 如果leftType == 0表示指向的是左子树，如果等于1则表示指向前驱节点
    private int leftType;
    // 如果rightType == 0表示指向的是右子树，如果等于1则表示指向后继节点
    private int rightType;

    public HeroNode1(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    public HeroNode1 getLeft() {
        return left;
    }

    public void setLeft(HeroNode1 left) {
        this.left = left;
    }

    public HeroNode1 getRight() {
        return right;
    }

    public void setRight(HeroNode1 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode1{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 栈的知识 回溯
     * 递归删除节点
     * 如果删除的节点是叶子节点，则删除该节点
     * 如果删除的节点是非叶子节点，则删除该子树
     * 1.因为二叉树是单向的，所以判断当前节点的子节点是否需要删除节点，而不能去判断当前这个节点是不是需要删除节点
     * 2.如果当前节点的左节点不能为空，并且左子节点就是要删除节点，就将this.left = null;并且返回(结束递归)
     * 3.如果当前节点的右节点不能为空，并且右子节点就是要删除节点，就将this.right = null;并且返回(结束递归)
     * 如果2和3没有删除节点，那么我们就需要向左子树递归删除
     * 如果第4也没有删除节点，则应当向右子树进行递归删除
     *
     * @param no 编号
     */
    public void delNode(int no) {
        // 如果当前节点的左节点不能为空，并且左子节点就是要删除节点，就将this.left = null;并且返回(结束递归)
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        // 如果当前节点的右节点不能为空，并且右子节点就是要删除节点，就将this.right = null;并且返回(结束递归)
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 向左子树递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        // 向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
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
    public HeroNode1 preOrderSearch(int no) {
        System.out.println("前序");
        // 比较当前节点是不是等于要查找的编号
        if (this.no == no) {
            return this;
        }
        // 判断当前节点的左节点是否为空，不为空，则递归前序查找
        // 左递归前序查找，找到节点则返回
        HeroNode1 resNode = null;
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
    public HeroNode1 infixOrderSearch(int no) {
        // 判断当前节点的左节点是否为空，不为空，则递归中序查找
        // 左递归中序查找，找到节点则返回
        HeroNode1 resNode = null;
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
    public HeroNode1 postOrderSearch(int no) {
        // 判断当前节点的左节点是否为空，不为空，则递归后序查找
        // 左递归中序查找，找到节点则返回
        HeroNode1 resNode = null;
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