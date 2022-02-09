package com.xxx.linkedList;

/**
 * 链表
 * 添加链表
 * 先创建一个head头节点，作用就是表示单链表的头
 * 后面我们每添加一个节点，就直接加入到链表的最后
 * 遍历：
 * 通过一个辅助遍历遍历，帮助遍历整个链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {

    }
}

// 定义SingleLinkedList管理英雄
class SingleLinkedList {
    // 初始化头节点
    private final HeroNode head = new HeroNode(0, "", "");

    // 添加节点到单向链表
    // 不考虑编号顺序
    // 1.找到当前链表的最后节点
    // 2. 将最后节点的next指向新的节点
    public void add(HeroNode heroNode) {
        // head节点不能改变，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
        // 遍历链表，找到最后  ------ temp为空时，为最后一个节点
        while (temp.next != null) {
            // 没有找到最后链表，将temp往后移
            temp = temp.next;
        }
        // 当退出while循环时，temp指向链表最后
        temp.next = heroNode;
    }

    // 显示链表
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (temp != null) {
            // 输出节点信息
            System.out.println(temp);
            // 将temp后移
            temp = temp.next;
        }
    }
}

// 定义HeroNode, 每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    // 构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", next=" + next +
                '}';
    }
}
