package com.xxx.linkedList;

import java.util.Stack;

/**
 * 演示栈Stack的基本使用
 * 先进后出的原则
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        // 入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        // 出栈
        while (stack.size() > 0) {
            // pop就是将栈顶的数据取出
            System.out.println(stack.pop());
        }
    }
}
