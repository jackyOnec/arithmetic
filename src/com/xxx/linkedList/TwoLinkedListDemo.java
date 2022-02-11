package com.xxx.linkedList;

/**
 * 分析过程：
 * 将两个有序的单链表合并成一个有序的单链表
 * <p>
 * 思路：
 * 1.利用第三个单链表存储两个单链表的节点，若两个链表均为空，则直接返回；
 * 2.若其中一个链表为空，则直接将另一个链表连接至链表3上；
 * 3.若两个链表均不为空，先将其中一个链表1连接至链表3，然后遍历另一个链表2，将链表2中的节点按照顺序连接至链表3上。
 * 4.若出现链表3遍历完毕但链表2尚未遍历完毕的情况，可以直接将链表2剩下的节点连接至链表3 尾部。
 * https://blog.csdn.net/weixin_44589991/article/details/115548846
 */
public class TwoLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        Node1 node1 = new Node1(1, "小一");
        Node1 node2 = new Node1(2, "小二");
        Node1 node3 = new Node1(3, "小三");
        Node1 node4 = new Node1(4, "小四");
        Node1 node5 = new Node1(5, "小五");
        Node1 node6 = new Node1(6, "小六");
        Node1 node7 = new Node1(7, "小七");
        Node1 node8 = new Node1(8, "小八");

        // 创建链表
        SingleLinkedList_h singleLinkedList1 = new SingleLinkedList_h();
        SingleLinkedList_h singleLinkedList2 = new SingleLinkedList_h();
        SingleLinkedList_h singleLinkedList3 = new SingleLinkedList_h();

        // 添加节点
        singleLinkedList1.addByOrder(node7);
        singleLinkedList1.addByOrder(node6);
        singleLinkedList1.addByOrder(node3);
        singleLinkedList1.addByOrder(node1);
        singleLinkedList2.addByOrder(node8);
        singleLinkedList2.addByOrder(node5);
        singleLinkedList2.addByOrder(node4);
        singleLinkedList2.addByOrder(node2);

        // 显示两个链表
        System.out.println("单链表1：");
        singleLinkedList1.list();
        System.out.println("单链表2：");
        singleLinkedList2.list();

        // 显示合并后的链表
        twoLinkedList(singleLinkedList1.getHead(), singleLinkedList2.getHead(), singleLinkedList3.getHead());
        System.out.println("组合后的链表：");
        singleLinkedList3.list();
    }

    // twoLinkedList方法
    // 传入待合并的两个链表的头节点以及第三个单链表的头节点
    public static void twoLinkedList(Node1 head1, Node1 head2, Node1 head3) {
        // 如果两个链表均为空，则无需合并，直接返回
        if (head1.next == null && head2.next == null) {
            return;
        }
        // 如果链表1为空，则将head3.next指向head2.next，实现链表2中的节点连接到链表3
        if (head1.next == null) {
            head3.next = head2.next;
        } else {
            // 将head3.next指向head1.next，实现链表1中的节点连接到链表3
            head3.next = head1.next;
            // 定义一个辅助的指针（变量），帮助我们遍历链表2
            Node1 cur2 = head2.next;
            // 定义一个辅助的指针（变量），帮助我们遍历链表3
            Node1 cur3 = head3;
            Node1 next = null;
            // 遍历链表2，将其节点按顺序连接至链表3
            while (cur2 != null) {
                // 链表3遍历完毕后，可以直接将链表2剩下的节点连接至链表3的末尾
                if (cur3.next == null) {
                    cur3.next = cur2;
                    break;
                }
                // 在链表3中，找到第一个大于链表2中的节点编号的节点
                // 因为是单链表，找到的节点是位于添加位置的前一个节点，否则无法插入
                if (cur2.no <= cur3.next.no) {
                    next = cur2.next;  // 先暂时保存链表2中当前节点的下一个节点，方便后续使用
                    cur2.next = cur3.next;  // 将cur2的下一个节点指向cur3的下一个节点
                    cur3.next = cur2;  // 将cur2连接到链表3上
                    cur2 = next;  // 让cur2后移
                }
                // 遍历链表3
                cur3 = cur3.next;
            }
        }
    }

}


//定义SingleLinkedList_h类管理节点
class SingleLinkedList_h {
    // 先初始化头节点，头节点不动
    private Node1 head = new Node1(0, "");

    //获取链表头节点
    public Node1 getHead() {
        return head;
    }

    // 添加节点时，根据编号按顺序将节点插入到指定位置
    // 如果链表中已有这个编号，则添加失败，并给出提示
    public void addByOrder(Node1 Node1) {
        // 头节点不能动，通过一个辅助指针（变量）帮助找到需要添加的位置
        // 因为是单链表，找到的temp是位于添加位置的前一个节点，否则无法插入
        Node1 temp = head;
        boolean flag = false;
        while (flag) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > Node1.no) {
                break;
            }
            if (temp.next.no == Node1.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("输入的编号%d已经存在，不能加入\n", Node1.no);
        } else {
            Node1.next = temp.next;
            temp.next = Node1;
        }
    }

    //显示链表【遍历】
    public void list() {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Node1 rec = head.next;
        while (true) {
            //判断链表是否到最后
            if (rec == null) {
                break;
            }
            System.out.println(rec);
            rec = rec.next;
        }
    }

}

//Node1类，每个Node1对象就是一个节点
class Node1 {
    public int no;
    public String name;
    public Node1 next;   //指向下一个节点

    //构造器
    public Node1(int no, String name) {
        this.no = no;
        this.name = name;
    }
    //为了显示方便，重新toString

    @Override
    public String toString() {
        return "Node1{" +
                "no=" + no +
                ", name='" + name +
                '}';
    }
}
