package com.xxx.hashTable;

import java.util.Scanner;

/**
 * 哈希表
 */
public class HashTableDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTab hashTab = new HashTab(7);

        // 菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    System.out.println("请输入名字");
                    String name = scanner.next();

                    // 创建雇员
                    Emp emp = new Emp(id, name);
            }
        }
    }
}

/**
 * 创建hashTab 管理多条链表
 */
class HashTab {
    private EmpLinkedList[] empLinkedListsArray;
    // 表示有多少条链表
    private int size;

    /**
     * 构造器
     *
     * @param size 大小
     */
    public HashTab(int size) {
        this.size = size;
        empLinkedListsArray = new EmpLinkedList[size];
    }

    /**
     * 添加雇员
     *
     * @param emp 雇员
     */
    public void add(Emp emp) {
        // 根据员工的id，得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        // 将emp添加到对应的链表中
        empLinkedListsArray[empLinkedListNo].add(emp);
    }

    /**
     * 遍历所有的链表，遍历hashTab
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListsArray[i].list();
        }
    }

    /**
     * 编写散列函数，一个简单取模法
     *
     * @param id 雇员id
     */
    public int hashFun(int id) {
        return id % size;
    }

}

/**
 * 雇员
 */
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

/**
 * 链表
 */
class EmpLinkedList {
    // 头指针执行第一个Emp，因此我们这个链表的head是直接指向第一个Emp
    private Emp head;

    /**
     * 添加雇员到链表
     * 假定添加雇员时，id是自增长的，即id的分配总是从小到大因此我们将该雇员直接加入到本链表的最后即可
     *
     * @param emp 雇员
     */
    public void add(Emp emp) {
        // 添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是第一个雇员，则使用一个辅助的指针帮助定位到最后
        Emp curEmp = this.head;
        // 说明到链表最后
        while (curEmp.next != null) {
            // 后移
            curEmp = curEmp.next;
        }
        // 退出时直接将emp加入链表
        curEmp.next = emp;
    }

    /**
     * 遍历链表雇员信息
     */
    public void list() {
        if (head == null) {
            // 说明链表为空
            System.out.println("当前链表为空");
        }
        System.out.println("当前链表的信息为：");
        // 辅助指针
        Emp curEmp = this.head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                // 说明curEmp已经是最后的节点
                break;
            }
            // 后移，遍历
            curEmp = curEmp.next;
        }
        System.out.println();
    }
}
