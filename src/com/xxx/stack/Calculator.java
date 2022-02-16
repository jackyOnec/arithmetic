package com.xxx.stack;

/**
 * 中缀表达式
 */
public class Calculator {
    public static void main(String[] args) {
        // 运算表达式
        String expression = "30+2*6-2";

        // 创建两个栈用于存放数字和运算符
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operatorStack = new ArrayStack2(10);

        // 定义需要的变量
        // 索引用于扫描
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int operator = 0;
        // 运算得到的结果
        int res = 0;
        // 将每次扫描得到char保存到ch
        char ch = ' ';
        // 用于拼接
        String keepNum = "";

        // 用while循环扫描expression
        while (true) {
            // 依次得到expression的每一个字符
            // 得到字符串expression.substring(index, index + 1)
            // .charAt(0)得到一个字符串
            ch = expression.substring(index, index + 1).charAt(0);

            // 判断是否是运算符
            if (operatorStack.isOperator(ch)) {
                if (!operatorStack.isEmpty()) {
                    // 符号栈有操作符,就进行比较,如果当前操作符的优先级小于或者德语栈中的操作符,就需要从数栈中pop出两个数字
                    // 在从符号栈中pop出一个符号,进行运算,将得到结果,入数栈,然后将当前的操作符入符号栈
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = operatorStack.pop();
                        res = numStack.cal(num1, num2, operator);
                        // 把运算结果入数栈
                        numStack.push(res);
                        operatorStack.push(ch);
                    } else {
                        // 如果当前的操作符的优先级大于栈中的操作符,就直接入符号栈
                        operatorStack.push(ch);
                    }
                } else {
                    // 为空直接入符号栈
                    operatorStack.push(ch);
                }
            } else {
                // ASCII码 数值相差48
//                numStack.push(ch - 48);
                /*
                  当处理多位数时,不能发现是一个数字就立即入栈,有可能是多位数
                  处理多位数时需要向expression的表达式的index后看一位,如果是数就进行扫描,如果是符号就入栈
                  需要定义一个变量用于拼接
                 */

                // 处理多位数
                keepNum += ch;

                // 如果ch已经是expression的最后一位,就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    // 判断是否是数字
                    // 判断是否是字符如果不是就是数字
                    if (operatorStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            // 让index+1,并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        // 数栈计算
        // 表达式扫描完毕,就顺序的从数栈和符号栈中pop出相应的数和符号,并运行
        while (true) {
            // 符号栈为空,则计算到最后的结果,数栈中只有一个数字
            if (operatorStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = operatorStack.pop();
            res = numStack.cal(num1, num2, operator);
            numStack.push(res);
        }
        // 将数栈的最后一位数 输出
        System.out.printf("表达式%s = %d", expression, numStack.pop());
    }
}

/**
 * 定义一个ArrayStack表示栈
 */
class ArrayStack2 {
    // 栈的大小
    private int maxSize;

    // 数组模拟栈，数据存放在该数组
    private int[] stack;

    // 栈顶，初始化为-1
    private int top = -1;

    public ArrayStack2(int maxSize) {
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
     * 返回栈顶
     */
    public int peek() {
        return stack[top];
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

    /**
     * 返回运算符的优先级,数字越大优先级越高
     *
     * @param operator 运算符
     * @return 优先级
     */
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            // 假定目前的表达式只有+, -, *, /
            return -1;
        }
    }

    /**
     * 判断是不是一个运算符
     *
     * @param val 运算符
     * @return 真假
     */
    public boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算方法
     *
     * @param num1     数值1
     * @param num2     数值2
     * @param operator 运算符
     * @return 结果
     */
    public int cal(int num1, int num2, int operator) {
        // 用于存放计算结果
        int res = 0;
        switch (operator) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}