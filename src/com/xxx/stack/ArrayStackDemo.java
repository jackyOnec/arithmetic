package com.xxx.stack;

import java.util.Scanner;

/**
 * 通过数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        String key;
        boolean loop = true;
        // 扫描器
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show(s):显示栈");
            System.out.println("exit(e):退出程序");
            System.out.println("push(push):入栈");
            System.out.println("pop(pop):出栈");
            System.out.println("请输入你的选择\n");

            key = scanner.next();
            switch (key) {
                case "s":
                    arrayStack.list();
                    break;
                case "e":
                    loop = false;
                    scanner.close();
                    break;
                case "push":
                    System.out.println("请输入一个数字\n");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = arrayStack.pop();
                        System.out.printf("出栈的数据是%d\n", pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
            System.out.println("程序退出");
        }
    }
}

/**
 * 定义一个ArrayStack表示栈
 */
class ArrayStack {
    // 栈的大小
    private int maxSize;

    // 数组模拟栈，数据存放在该数组
    private int[] stack;

    // 栈顶，初始化为-1
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 栈满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param value 值
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈，将栈顶的数据返回
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据");
        }

        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 显示列表
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        // 需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
