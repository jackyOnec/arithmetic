package com.xxx.stack;

/**
 * 链表模拟栈
 * 可参考
 */
public class linkedListStackDemo {
    public static void main(String[] args) {
        ListStack listStack = new ListStack(4);
        listStack.push(1);
        listStack.push(2);
        listStack.push(3);
        listStack.push(4);
        Object pop = listStack.pop();
        System.out.println("出栈 " + pop);
        Object peak = listStack.peak();
        System.out.println("栈顶是  " + peak);

        System.out.println("显示链表");
        listStack.show();
    }
}

class ListStack {
    linkedListStack header;//栈顶元素
    int elementCount;//栈内元素个数
    int size;//栈的大小

    /**
     * 构造函数，构造一个空的栈
     */
    public ListStack() {
        header = null;
        elementCount = 0;
        size = 0;
    }

    /**
     * 通过构造器自定义栈的大小
     *
     * @param size 大小
     */
    public ListStack(int size) {
        header = null;
        elementCount = 0;
        this.size = size;
    }

    public void setHeader(linkedListStack header) {
        this.header = header;
    }

    public boolean isFull() {
        return elementCount == size;
    }

    public boolean isEmpty() {
        return elementCount == 0;
    }

    /**
     * 入栈
     *
     * @param value 值
     */
    public void push(Object value) {
        if (this.isFull()) {
            throw new RuntimeException("Stack is Full");
        }
        //注意这里面试将原来的header作为参数传入，然后以新new出来的linkedListStack作为header
        header = new linkedListStack(value, header);
        elementCount++;
    }

    /**
     * 出栈
     */
    public Object pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        Object object = header.getElement();
        header = header.next;
        elementCount--;
        return object;
    }

    /**
     * 返回栈顶元素
     */
    public Object peak() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return header.getElement();
    }

    public void show() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        while (header != null) {
            System.out.println(header.getElement());
            header = header.next;
        }
    }
}

class linkedListStack {
    Object element;
    linkedListStack next;

    public linkedListStack(Object element) {
        this(element, null);
    }

    /**
     * 创建一个新的节点
     * 让他的next指向，参数中的节点
     *
     * @param element element
     * @param n       next
     */
    public linkedListStack(Object element, linkedListStack n) {
        this.element = element;
        next = n;
    }

    public Object getElement() {
        return element;
    }
}
