package com.xxx.base;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Calendar;

public class Base64Utils {
    /**
     * 图片转化成base64字符串
     *
     * @param imgPath
     * @return
     */
    public static String GetImageStr(String imgPath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = imgPath;// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        String encode = null; // 返回Base64编码过的字节数组字符串
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            // 读取图片字节数组
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            encode = encoder.encode(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return encode;
    }

    /**
     * base64字符串转化成图片
     *
     * @param imgData     图片编码
     * @param imgFilePath 存放到本地路径
     * @return
     * @throws IOException
     */
    @SuppressWarnings("finally")
    public static boolean GenerateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
        if (imgData == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgData);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            return true;
        }
    }

    /**
     * 创建文件夹
     *
     * @param src 路径
     */
    public static void createFile(String src) {
        // path表示你所创建文件的路径
        String path = src.substring(0, src.lastIndexOf("/"));
        // fileName表示你创建的文件名
        String fileName = src.substring(src.lastIndexOf("/") + 1, src.length());
        File f = new File(path);
        if (!f.exists() && !f.isDirectory()) {
            f.mkdirs();
        } else {
            System.out.println("文件存在");
        }
        // 创建文件
       /* File file = new File(f, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    public static void main(String[] args) throws IOException {
//        String imageStr = Base64Utils.GetImageStr("C://Users//xxx//Pictures//1642597110878.jpg");
//        System.out.println(imageStr);
//        String str = "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";
//        Base64Utils.GenerateImage(str, "E:/project/RuoYi/xxxx/uploadPath/upload/企鹅.jpg");
        Calendar cal = Calendar.getInstance();
        DecimalFormat g1 = new DecimalFormat("00");
        String day = g1.format(cal.get(Calendar.DATE));
        String month = g1.format(cal.get(Calendar.MONTH) + 1);
        int year = cal.get(Calendar.YEAR);
        String des1 = "E:/project/RuoYi/xxxx/uploadPath/registration/" + year + "/" + month + "/" + day + "/";
        createFile(des1);
    }
}