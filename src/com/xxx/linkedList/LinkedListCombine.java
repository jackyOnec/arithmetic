package com.xxx.linkedList;

/**
 * 合并两个有序的单链表，合并之后的链表依然有序
 * 新建一个单链表，每次都把两个有序链表中的更小的值加入到新链表中
 */
public class LinkedListCombine {
    public static void main(String[] args) {
        SingleLinkedList4 singleLinkedList1 = new SingleLinkedList4();
        SingleLinkedList4 singleLinkedList2 = new SingleLinkedList4();

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        singleLinkedList1.add(node3);
        singleLinkedList1.add(node1);
        singleLinkedList1.add(node7);
        singleLinkedList1.add(node5);
        singleLinkedList2.add(node2);
        singleLinkedList2.add(node6);
        singleLinkedList2.add(node8);
        singleLinkedList2.add(node4);

        System.out.println("链表一：");
        show(singleLinkedList1.head);
        System.out.println("链表二：");
        show(singleLinkedList2.head);

        System.out.println("合并两个链表==========");
        Node combine = singleLinkedList1.combine(singleLinkedList1.head, singleLinkedList2.head);
        show(combine);

    }

    /**
     * 打印显示链表
     *
     * @param head 链表头
     */
    public static void show(Node head) {
        Node cur = head.next;
        if (cur == null) {
            System.out.println("链表为空~");
        }
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }
}

class SingleLinkedList4 {
    public Node head;

    public SingleLinkedList4() {
        head = new Node(-1);
    }

    /**
     * 数据按从小到大的顺序添加到链表中
     *
     * @param newNode 新节点
     */
    public void add(Node newNode) {
        //辅助变量必须从head开始，不能从head.next开始，否则链表就断了
        Node cur = head;
        Node pre = head;
        int value = newNode.num;
        while (cur.next != null) {
            pre = cur;
            cur = cur.next;
            if (value <= cur.num) {  //小于节点cur，插在cur前面
                newNode.next = cur;
                pre.next = newNode;
                return; //当前链表中存在大于等于value的值
            }
        }
        cur.next = newNode; //当前链表中不存在大于等于value的值，则添加到末尾
    }

    public Node combine(Node head1, Node head2) {
        Node combine = new Node(-1);
        Node combineTemp = combine;
        Node cur1 = head1.next;
        Node cur2 = head2.next;
        while (cur1 != null && cur2 != null) {
            if (cur1.num <= cur2.num) {
                combineTemp.next = new Node(cur1.num);
                combineTemp = combineTemp.next;
                cur1 = cur1.next;
            } else {
                combineTemp.next = new Node(cur2.num);
                combineTemp = combineTemp.next;
                cur2 = cur2.next;
            }
        }
        if (cur1 == null) {
            while (cur2 != null) {
                combineTemp.next = new Node(cur2.num);
                combineTemp = combineTemp.next;
                cur2 = cur2.next;
            }
        } else {
            while (cur1 != null) {
                combineTemp.next = new Node(cur1.num);
                combineTemp = combineTemp.next;
                cur1 = cur1.next;
            }
        }
        return combine;
    }
}

class Node {
    public int num;
    public Node next;

    public Node(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                '}';
    }
}