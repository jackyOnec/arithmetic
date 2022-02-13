package com.xxx.linkedList;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 创建人物
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        HeroNode2 hero5 = new HeroNode2(5, "林冲冲", "豹子头头");
        HeroNode2 hero6 = new HeroNode2(6, "林冲呀", "豹子头呀");
        HeroNode2 hero7 = new HeroNode2(7, "林冲啊", "豹子头啊");
        HeroNode2 hero8 = new HeroNode2(8, "林冲嗯", "豹子头额");

        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        System.out.println("修改双向链表");

        HeroNode2 newHeroNode2 = new HeroNode2(2, "小路", "小路路路路");
        doubleLinkedList.update(newHeroNode2);

        doubleLinkedList.list();

        System.out.println("删除");
        // 删除节点
        doubleLinkedList.delete(3);

        doubleLinkedList.list();

        System.out.println("顺序添加");
        DoubleLinkedList list = new DoubleLinkedList();

        list.addByOrder(hero8);
        list.addByOrder(hero5);
        list.addByOrder(hero1);
        list.addByOrder(hero3);
        list.addByOrder(hero7);

        list.list();
    }
}

/**
 * 创建双向链表的类
 */
class DoubleLinkedList {
    // 初始化头节点
    private final HeroNode2 head = new HeroNode2(0, "", "");

    /**
     * 返回头节点
     */
    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 显示双向链表
     */
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (temp != null) {
            // 输出节点信息
            System.out.println(temp);
            // 将temp后移
            temp = temp.next;
        }
    }

    /**
     * 添加双向链表
     *
     * @param heroNode 节点
     */
    public void add(HeroNode2 heroNode) {
        // head节点不能改变，因此我们需要一个辅助遍历 temp
        HeroNode2 temp = head;
        // 遍历链表，找到最后  ------ temp为空时，为最后一个节点
        while (temp.next != null) {
            // 没有找到最后链表，将temp往后移
            temp = temp.next;
        }
        // 当退出while循环时，temp指向链表最后
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 按照编号顺序添加
     *
     * @param heroNode 节点
     */
    public void addByOrder(HeroNode2 heroNode) {
        // 节点头不能移动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加位置
        // 因为是单链表，我们找到的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode2 temp = head;
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
            // 当添加第一个节点编号时，没有下下节点时，会出现空指针异常
            // temp.next已经为空，是没有指向的，会出现空指针的情况
            if (temp.next != null) {
                // 待添加元素next指向temp的下一个节点
                heroNode.next = temp.next;
                // temp的下一个节点的pre指向待插入节点
                temp.next.pre = heroNode;
            }
            // 上一跳的next指向当前节点 temp的next节点指向heroNode节点
            temp.next = heroNode;
            // 当前节点的pre指向上一跳节点，形成双向链表   heroNode的pre指向temp节点
            heroNode.pre = temp;
        }
    }

    /**
     * 修改双向链表
     *
     * @param newHeroNode 修改的节点
     */
    public void update(HeroNode2 newHeroNode) {
        // 判断是否空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点，根据no编号
        // 定义一个辅助变量
        HeroNode2 temp = head.next;
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
     * 删除双向链表
     * 对于双向链表，我们可以直接找到要删除的这个节点
     *
     * @param no 编号
     */
    public void delete(int no) {
        // 判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }

        // 辅助变量（指针）
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                // 已经遍历完链表
                break;
            }
            if (temp.no == no) {
                // 找到节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            // 找到节点
            temp.pre.next = temp.next;
            // 如果是最后一个节点，就不需要执行下面语句，否则会出现空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("没有找到编号%d的节点，不能修改\n", no);
        }
    }
}

/**
 * 定义HeroNode, 每个HeroNode对象就是一个节点
 */
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    // 指向前一个节点
    public HeroNode2 pre;

    // 构造器
    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
