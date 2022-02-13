package com.xxx.linkedList;

/**
 * 约瑟夫环
 */
public class Joseph {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        System.out.println("出列···");
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

/**
 * 创建一个环形单向链表
 */
class CircleSingleLinkedList {
    // 创建一个first节点，
    private Boy first = new Boy(-1);

    /**
     * 添加boy节点，构建成一个环形的链表
     *
     * @param nums boy的数量
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        // 辅助指针，帮助构建环形链表
        Boy curBoy = null;
        // 使用for循环来创环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建boy环形链表
            Boy boy = new Boy(i);
            // 第一个节点
            if (i == 1) {
                first = boy;
                // 构建成环
                first.setNext(first);
                // 让curBoy指向第一个boy
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历当前的环形链表
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("没有任何数据");
            return;
        }
        // 因为first不能改变，因此我们需要一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("Boy的编号是 %d \n", curBoy.getNo());
            // 已经遍历完成
            if (curBoy.getNext() == first) {
                break;
            }
            // curBoy往后移动
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 计算Boy出列顺序
     *
     * @param startNo  第几个开始
     * @param countNum 共几下
     * @param nums     boy的数量
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入错误");
            return;
        }
        // 创建辅助变量
        Boy helper = first;
        // 创建一个辅助指针（变量）helper，指向环形链表最后一个节点
        // helper.getNext() 指向的是下一个节点
        // 因为这是一个环如果 helper.getNext() == first 说明到达了最后一个节点
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        System.out.println("helper = " + helper.getNo());
        System.out.println("first = " + first.getNo());

        // 先让first和helper移动 k - 1 次
        // 第几个开始
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        System.out.println("first1 = " + first.getNo());
        System.out.println("helper1 = " + helper.getNo());

        // 让first和helper指针同时移动 m-1 次，然后出列
        // helper == first 已经到到了同一个节点上
        while (helper != first) {
            // 让 first 和 helper 指针同时移动 countNum - 1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这时first指向的节点，结束出列的节点
            System.out.printf("boy%d出列了\n", first.getNo());
            // 将first指向的boy出列
            first = first.getNext();
            // 修改上一节个点的下一跳位置
            helper.setNext(first);
        }
        System.out.printf("最后一个boy%d出列了 \n", first.getNo());
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
