package com.xxx.linkedList;

/**
 * 约瑟夫环
 */
public class Joseph {
    public static void main(String[] args) {

    }
}

/**
 * 创建一个环形单向链表
 */
class CircleSingleLinkedList {
    // 创建一个first节点，
    private Boy first = new Boy(-1);

    /**
     * 添加小孩节点，构建成一个环形的链表
     *
     * @param nums
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        // 使用for循环来创链表
    }
}

/**
 * 创建一个Boy类，表示一个节点
 */
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
