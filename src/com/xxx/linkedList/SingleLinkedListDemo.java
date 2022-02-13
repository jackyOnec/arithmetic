package com.xxx.linkedList;

import java.util.Stack;

/**
 * 链表
 * 添加链表
 * 先创建一个head头节点，作用就是表示单链表的头
 * 后面我们每添加一个节点，就直接加入到链表的最后
 * 遍历：
 * 通过一个辅助遍历，帮助遍历整个链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 创建人物
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        HeroNode hero5 = new HeroNode(5, "林冲冲", "豹子头头");
        HeroNode hero6 = new HeroNode(6, "林冲呀", "豹子头呀");
        HeroNode hero7 = new HeroNode(7, "林冲啊", "豹子头啊");
        HeroNode hero8 = new HeroNode(8, "林冲嗯", "豹子头额");

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        // 按照编号排序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);

        // 修改测试
        HeroNode newHeroNode = new HeroNode(2, "小路", "小路路路路");
        singleLinkedList.update(newHeroNode);

        // 删除节点
        singleLinkedList.delete(1);

        // 显示
        singleLinkedList.list();

        // 求单链表中有效节点的个数
        System.out.println("有效节点个数=" + getLength(singleLinkedList.getHead()));

        // 得到倒数第K个节点
        HeroNode lastIndexNode = findLastIndexNode(singleLinkedList.getHead(), 1);
        System.out.println("lastIndexNode = " + lastIndexNode);

        // 链表反转
        reverseList(singleLinkedList.getHead());
        // 显示
        singleLinkedList.list();

        // 栈反转
        System.out.println("~~~~~~~~~~~");
        reversePrint(singleLinkedList.getHead());

        SingleLinkedList list = new SingleLinkedList();

        list.addByOrder(hero1);
        list.addByOrder(hero5);
        list.addByOrder(hero3);
        list.addByOrder(hero7);

        System.out.println("合并前list--------");
        list.list();

        SingleLinkedList list2 = new SingleLinkedList();
        list2.addByOrder(hero4);
        list2.addByOrder(hero6);
        list2.addByOrder(hero2);
        list2.addByOrder(hero8);

        System.out.println("合并前list2--------");
        list2.list();

        System.out.println("合并后--------");

        //合并两个链表
        HeroNode heroNode = mergeList(list.getHead(), list2.getHead());
        list.show(heroNode);
    }

    /**
     * 方法：获取到单链表的节点的个数（如果是带头节点的链表，需求不统计头节点）
     *
     * @param head 链表的头节点
     * @return 返回链表个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            // 空链表
            return 0;
        }
        int length = 0;
        // 定义辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;  // 遍历
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第K个节点
     * 思路
     * 编写一个方法，接收head节点，同时接收一个index
     * index：倒数第index个节点
     * 先把链表从头到尾遍历，得到链表的总长度getLength
     * 得到size后，我们从链表的第一个开始遍历（size - index）个，就可得到
     * 找到返回该节点，否则返回null
     *
     * @param head  头节点
     * @param index 倒数第index个节点
     * @return 返回HeroNode对象
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            // 没找到
            return null;
        }
        // 遍历得到链表的长度（节点的个数）
        int size = getLength(head);
        // 校验index
        if (index <= 0 || index > size) {
            return null;
        }

        // size - index 位置，就是我们倒数的第K个节点
        // 定义辅助变量，for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 将单链表反转
     *
     * @param head 头节点
     */
    public static void reverseList(HeroNode head) {
        // 如果当前链表为空，或者只有一个节点，无需反转直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        // 定义一个辅助的指针变量，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; // 指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");

        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur != null) {
            // 先暂时保存当前节点的下一个节点
            next = cur.next;
            // 将cur的下一个节点指向新的链表的最前端
            cur.next = reverseHead.next;
            // 将cur连接到新的链表上
            reverseHead.next = cur;
            // 让cur后移
            cur = next;
        }
        // 将head.next指向reverseHead.next,实现单链表反转
        head.next = reverseHead.next;
    }

    /**
     * 利用栈的数据结构，将各个节点压入到栈中，利用栈的先进后出特点，实现逆序打印的效果
     *
     * @param head 头节点
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            // 空链表，不能打印
            return;
        }
        // 创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        // 将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 将栈中的节点打印,pop出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 有序的单链表
     * 合并两个有序的单链表 使合并完成之后的新的链表依旧有序
     * https://www.cnblogs.com/dong9012/p/13034333.html
     *
     * @param hero1 链表一
     * @param hero2 链表二
     */
    public static HeroNode mergeList(HeroNode hero1, HeroNode hero2) {
        //创建一个新的节点
        HeroNode hero3 = new HeroNode(0, "", "");

        HeroNode temp1 = hero1.next;
        HeroNode temp2 = hero2.next;
        HeroNode temp3 = hero3;

        HeroNode next1 = null;//用于保存下一个节点
        HeroNode next2 = null;

        while (temp1 != null && temp2 != null) {
            // 比较链表之间的编号
            if (temp1.no < temp2.no) {
                // 先保存下一个节点
                next1 = temp1.next;
                // 将较小的节点插入到新的节点后面
                temp3.next = temp1;
                // 将新的节点后移一位 注意:一定要后移
                temp3 = temp3.next;
                // 将节点后移一位
                temp1 = next1;
            } else {
                next2 = temp2.next;
                temp3.next = temp2;
                temp3 = temp3.next;
                temp2 = next2;
            }
        }

        // 节点的长度不一样时
        if (temp1 == null) {
            while (temp2 != null) {
                next2 = temp2.next;
                temp3.next = temp2;
                temp3 = temp3.next;
                temp2 = next2;
            }
        } else {
            while (temp1 != null) {
                // 先保存下一个节点
                next1 = temp1.next;
                // 将较小的节点插入到新的节点后面
                temp3.next = temp1;
                // 将新的节点后移一位 注意:一定要后移
                temp3 = temp3.next;
                // 将节点后移一位
                temp1 = next1;
            }
        }
        return hero3;
    }
}

/**
 * 定义SingleLinkedList管理英雄
 */
class SingleLinkedList {
    // 初始化头节点
    private final HeroNode head = new HeroNode(0, "", "");

    /**
     * 返回头节点
     */
    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表
     * 不考虑编号顺序
     * 1.找到当前链表的最后节点
     * 2. 将最后节点的next指向新的节点
     *
     * @param heroNode 英雄节点
     */
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

    /**
     * 第二种方法在添加英雄时，根据排名将英雄插入到指定位置
     * （如果有这个排名，则添加失败，并给出提示）
     * 每添加一次需要遍历一次链表。每个链表都是一个独立的空间，只是更改next存放的下一跳和内容。
     *
     * @param heroNode 英雄节点
     */
    public void addByOrder(HeroNode heroNode) {
        // 节点头不能移动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加位置
        // 因为是单链表，我们找到的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; // flag标志添加的编号是否存在，默认false
        while (true) {
            if (temp.next == null) {
                // 说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {
                // 位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {
                // 说明希望添加的heroNode的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next; // 后移遍历当前链表
        }
        // 判断flag的值
        if (flag) {
            // 不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号%d已存在了，不能加入\n", heroNode.no);
        } else {
            // 插入到链接中,temp的后面
            // 关键点在于这里
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点的信息，根据no编号来修改，即no编号不能改
     * 根据newHeroNode的no来修改即可
     *
     * @param newHeroNode 更新的节点
     */
    public void update(HeroNode newHeroNode) {
        // 判断是否空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点，根据no编号
        // 定义一个辅助变量
        HeroNode temp = head.next;
        // 表示是否找到节点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                // 已经遍历完链表
                break;
            }
            if (temp.no == newHeroNode.no) {
                // 找到节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 修改节点信息
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号%d的节点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 删除节点
     * head不能动，因此需要一个temp辅助节点找到待删除节点的前一个节点
     * 比较时是temp.next.no 和 需要删除的节点的no比较
     *
     * @param no 编号
     */
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                // 已经遍历完链表
                break;
            }
            if (temp.next.no == no) {
                // 找到节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            // 找到节点
            // 替换掉前节点到下下个节点
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到编号%d的节点，不能修改\n", no);
        }
    }

    /**
     * 显示链表
     */
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

    /**
     * 显示链表
     *
     * @param head 头节点
     */
    public void show(HeroNode head) {
        HeroNode cur = head.next;
        if (cur == null) {
            System.out.println("链表为空~");
            return;
        }
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }
}

/**
 * 定义HeroNode, 每个HeroNode对象就是一个节点
 */
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
                '}';
    }
}
