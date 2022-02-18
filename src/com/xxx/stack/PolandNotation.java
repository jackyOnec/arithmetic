package com.xxx.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 * 支持小括号和多位数整数，因为这里只针对数据结构，只支持对整数的计算
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 中缀表达式转为后缀表达式
        // 1+((2+3)*4)-5 => 转为 1 2 3 + 4 * + 5 -
        // 因为直接对str进行操作，不方便，先将"1+((2+3)*4)-5" => 中缀表达式对应List
        // 即 "1+((2+3)*4)-5" => ArrayList [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        String expression = "1+((2+3)*4)-5";
        // 中缀表达式
        List<String> strings = toInfixExpressionList(expression);
        System.out.println("中缀表达式 " + strings);

        // ArrayList [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] 转换为 ArrayList[1, 2, 3, +, 4, *, +, 5, -]
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(strings);
        System.out.println("中缀表达式转成后缀表达式 " + parseSuffixExpressionList);
        // 计算结果
        int calculate1 = calculate(parseSuffixExpressionList);
        System.out.println("结果 = " + calculate1);

        System.out.println("----------------------------------");

        // 先定义逆波兰表达式
        // 为了方便，逆波兰表达式的数字和符号使用空格隔开
        // (30+4)*5-6
        String suffixExpression = "30 4 + 5 * 6 -";
        // 思路
        // 先将 3 4 + 5 * 6 - 放到ArrayList中
        // 将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算

        List<String> list = getListString(suffixExpression);
        System.out.println("list = " + list);
        int calculate = calculate(list);
        System.out.println("calculate = " + calculate);
    }

    /**
     * 将得到中缀表达式对应的list转为后缀表达式
     *
     * @param list 中缀表达式
     */
    public static List<String> parseSuffixExpressionList(List<String> list) {
        // 定义两个栈
        // 符号栈
        Stack<String> s1 = new Stack<>();
        // 结果栈 在转换的过程中没有出栈pop操作，而且后面还需要逆序输出，可以使用List<String> s2
        ArrayList<String> s2 = new ArrayList<>();

        // 遍历list
        for (String item : list) {
            // 如果是一个数就加入S2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号")"，则依次弹出s1栈顶的运算符，并压入s2，直接遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                // 将（ 弹出s1的栈 消除括号
                s1.pop();
            } else {
                // 当item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次判断如果s1为空，
                // 或栈顶运算符为左括号“(”，则直接将此运算符入栈与s1中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 将item压入栈
                s1.push(item);
            }
        }
        // 将s1中剩余的运算符依次加入到s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }

    /**
     * 中缀表达式转为对应的List
     *
     * @param s 表达式
     */
    public static List<String> toInfixExpressionList(String s) {
        ArrayList<String> list = new ArrayList<>();
        // 相当一个指针，用于遍历中缀表达式
        int i = 0;
        // 多位数拼接
        String str;
        // 每遍历一个字符，放入到c
        char c;
        do {
            // 如果c是一个非数字，就加入到list中  符号
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add(String.valueOf(c));
                // i需要往后移
                i++;
            } else {
                // 如果是一个数，需要考虑多位数
                // 先将str置成"" '0'[48] -> '9'[57]
                str = "";
                // 遍历多位数
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    // 拼接数字
                    str += c;
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }

    /**
     * 将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
     *
     * @param suffixExpression 后缀表达式
     */
    public static List<String> getListString(String suffixExpression) {
        // 将suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        // 将集合复制到数字里面
        Collections.addAll(list, split);
        return list;
    }

    /**
     * 完成对逆波兰表达式的运算
     *
     * @param ls 数组
     */
    public static int calculate(List<String> ls) {
        // 创建栈
        Stack<String> stack = new Stack<>();
        // 遍历ls
        for (String item : ls) {
            // 使用正则表达式取出数  匹配多位数
            if (item.matches("\\d+")) {
                // 入栈
                stack.push(item);
            } else {
                // pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                // 存放结果
                int res = 0;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                // 把结果res 入栈
                stack.push(String.valueOf(res));
            }
        }
        // 最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}

/**
 * 返回运算符优先级
 */
class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    /**
     * 返回对应的优先级数字
     *
     * @param operation 运算符
     */
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}