package com.xxx.huffmanCode;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码---压缩、解压
 */
public class HuffmanCode {
    /*
     * 生成赫夫曼树对应的赫夫曼编码
     * 将赫夫曼编码表存放在Map<Byte, String> 形式
     * ASCII码->赫夫曼编码
     * 32->01 97->100 100->11000等等
     */
    static Map<Byte, String> huffmanCode = new HashMap<Byte, String>();
    // 在生成赫夫曼编码表示，需要去拼接路径，定义一个StringBuilder 存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        /*String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println("contentBytes.length = " + contentBytes.length);

        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes = " + nodes);

        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        preOrder(huffmanTreeRoot);
        System.out.println("huffmanTreeRoot = " + huffmanTreeRoot);

        System.out.println("赫夫曼编码表");
        Map<Byte, String> codes = getCodes(huffmanTreeRoot);
        System.out.println("codes = " + codes);

        System.out.println("赫夫曼编码--压缩");
        byte[] bytes = zip(contentBytes, codes);
        System.out.println("bytes = " + Arrays.toString(bytes));

        byte[] huffmanZip = huffmanZip(contentBytes);
        System.out.println("赫夫曼编码压缩过后：" + Arrays.toString(huffmanZip) + "长度：" + huffmanZip.length);

        System.out.println("赫夫曼解码");
        byte[] sourceBytes = decode(huffmanCode, huffmanZip);
        System.out.println("sourceBytes = " + new String(sourceBytes));*/

//        System.out.println("文件压缩");
//        String srcFile = "C:\\Users\\xx\\Pictures\\1622767905938.jpg";
//        String dstFile = "C:\\Users\\xx\\Pictures\\1622767905938.zip";
//        zipFile(srcFile, dstFile);
//        System.out.println("压缩成功");

        System.out.println("文件解压");
        String zipFile = "C:\\Users\\xx\\Pictures\\1622767905938.zip";
        String dstFile2 = "C:\\Users\\xx\\Pictures\\1622767905938(1).jpg";
        unZipFile(zipFile, dstFile2);
        System.out.println("解压成功");
    }

    /**
     * 对文件进行解压
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        // 定义文件输入流
        InputStream inputStream = null;
        // 定义一个对象输入流
        ObjectInputStream objectInputStream = null;
        // 定义文件的输出流
        OutputStream outputStream = null;
        try {
            // 创建文件输入流
            inputStream = new FileInputStream(zipFile);
            // 创建一个和inputStream关联的对象输入流
            objectInputStream = new ObjectInputStream(inputStream);
            // 读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) objectInputStream.readObject();
            // 读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) objectInputStream.readObject();

            // 解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            // 将bytes数组写入到目标文件
            outputStream = new FileOutputStream(dstFile);
            // 写数据到文件中
            outputStream.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                assert outputStream != null;
                outputStream.close();
                objectInputStream.close();
                inputStream.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 将文件进行压缩
     *
     * @param srcFile 传入的希望压缩的文件全路径
     * @param dstFile 我们压缩后将压缩文件放到那个目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        // 创建输出流
        OutputStream outputStream = null;
        //
        ObjectOutputStream objectOutputStream = null;
        // 创建文件的输入流
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            // 创建一个和源文件大小一样的byte[]
            byte[] bytes = new byte[fileInputStream.available()];
            // 读取文件
            fileInputStream.read(bytes);
            // 直接对源文件压缩
            byte[] huffmanZip = huffmanZip(bytes);
            // 创建文件的输出流，存放压缩文件
            outputStream = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的ObjectOutputStream
            objectOutputStream = new ObjectOutputStream(outputStream);
            // 把赫夫曼编码后的字节数组写入压缩文件
            objectOutputStream.writeObject(huffmanZip);
            // 我们以对象流的方式写入赫夫曼编码，是为了以后我们恢复源文件时使用
            // 注意，要把赫夫曼编码写入压缩文件
            objectOutputStream.writeObject(huffmanZip);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                assert fileInputStream != null;
                fileInputStream.close();
                assert objectOutputStream != null;
                objectOutputStream.close();
                outputStream.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 编写一个方法，完成对压缩数据的解码
     *
     * @param huffmanCode  哈弗吗编码表
     * @param huffmanBytes 哈弗吗编码得到的字节数组
     * @return 得到原来字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCode, byte[] huffmanBytes) {
        // 先得到huffmanBytes 对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 将byte数组转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        // 把字符串按照指定的赫夫曼编码进行解码
        // 把赫夫曼编码表进行调换，反向查询 a->100 100->a
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 创建要给集合，存放byte
        ArrayList<Object> list = new ArrayList<>();
        // i 可以理解成就是索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            // 小的计数器
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                // 取出一个 ’1‘ ’0‘
                // i 不动，让count移动，指定匹配到一个字符
                // substring截取字符串
                String key = stringBuilder.substring(i, i + count);
                // 取出对应的字母
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            // i 直接移动到count
            i += count;
        }
        // 当for循环结束后，我们list中就存放了所有的字符
        // 把list中的数据放入到byte[]并返回
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) list.get(i);
        }
        return bytes;
    }

    /**
     * 解码
     * 将一个byte转成一个二进制的字符串
     * 二进制原码、反码、补码
     *
     * @param flag 标志是否需要补高位如果是true，表示需要补高位，如果是false表示不补,如果是最后一个字节，无需补高位
     * @param b    二进制数据
     * @return b对应的二进制字符串(按补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用变量保存b 将b转成int
        int temp = b;
        // 如果是正数我们还存在不高位
        if (flag) {
            // 按位与 256 1 0000 0000 | 0000 0001 => 1 0000 0001
            temp |= 256;
        }
        // 返回的是temp对应的二进制补码
        String string = Integer.toBinaryString(temp);
        if (flag) {
            return string.substring(string.length() - 8);
        } else {
            return string;
        }
    }

    /**
     * 封装起来，方便调用
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 处理后赫夫曼压缩后的数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        // 创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        // 对应的赫夫曼编码(根据赫夫曼树)
        Map<Byte, String> codes = getCodes(huffmanTreeRoot);
        // 根据生成赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        return zip(bytes, codes);
    }

    /**
     * 将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
     * 举例： String content = "i like like like java do you like a java"; => byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * * => 对应的 byte[] huffmanCodeBytes，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * * huffmanCodeBytes[0] = 10101000(补码) => byte[推导 10101000 => 10101000 - 1 => 10100111(反码) => 11011000 = -88 ]
     * * huffmanCodeBytes[1] = -88
     * 正数的补码是它本身，负数的补码是反码加 每8位转为一个十进制数字。8位中第一位为正负号。 补码 = 反码 + 1 二进制的原码反码补码
     *
     * @param bytes       原始的字符串对应的byte[]
     * @param huffmanCode 生成赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCode) {
        // 利用huffmanCode将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCode.get(b));
        }
//        System.out.println("stringBuilder.toString() = " + stringBuilder.toString());
        // 统计返回byte[] huffmanCodeBytes长度
        // 也可以一句话 int len = (stringBuilder.length() + 7) / 8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        // 记录是第几个byte
        int index = 0;
        // 因为每8位对应一个byte，所有步长 + 8
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将strByte转成一个byte，放入huffmanCodeBytes里面
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 重写getCodes
     *
     * @param root 赫夫曼树
     * @return 赫夫曼编码
     */
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        // 处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        // 处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCode;
    }

    /**
     * 将传入的node节点的所有叶子节点的赫夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node          传入节点
     * @param code          路径:左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将code加入到StringBuilder2
        stringBuilder2.append(code);

        // 如果node == null不处理
        if (node != null) {
            // 判断当前node是叶子节点还是非叶子节点
            if (node.data == null) { // 非叶子节点
                // 递归处理 向左递归
                getCodes(node.left, "0", stringBuilder2);
                // 向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCode.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * 获取字节出现的次数
     *
     * @param bytes 接收字节数组
     * @return 返回的接收List形式
     */
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        // 遍历bytes，统计每一个byte出现的次数 -> map[key, value]
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                // Map还没有这个字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        // 把每个键值对转为一个node对象，并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getValue(), entry.getKey()));
        }
        return nodes;
    }

    /**
     * 通过List创建对应的赫夫曼树
     *
     * @param nodes 节点
     * @return 赫夫曼树
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序，从小到大
            Collections.sort(nodes);
            // 取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            // 取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            // 创建一颗新的二叉树，它的根节点没有data，只有权值
            Node parent = new Node(leftNode.weight + rightNode.weight, null);
            parent.left = leftNode;
            parent.right = rightNode;

            // 将已经处理的两颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将新的二叉树加入到nodes
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 前序遍历
     *
     * @param root 节点
     */
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树，不能遍历");
        }
    }
}

/**
 * 创建节点类
 * 为了让Node对象持续排序 Collections集合排序
 * 让Node实现Comparable接口
 */
class Node implements Comparable<Node> {
    // 节点权值,字符出现的次数
    int weight;
    // 指向左子节点
    Node left;
    // 指向右子节点
    Node right;
    // 存放数据(字符)本身，比如'a' => 97 ' ' => 32
    Byte data;

    public Node(int weight, Byte data) {
        this.weight = weight;
        this.data = data;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                ", data=" + data +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
