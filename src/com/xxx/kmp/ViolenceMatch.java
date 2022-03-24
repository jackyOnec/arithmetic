package com.xxx.kmp;

/**
 * 暴力匹配算法
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "测试测试测试长沙查收 初始初始从  初始成熟市场 初始从测试测试测试";
        String str2 = "初始从测试测试测试";
        int violenceMatch = violenceMatch(str1, str2);
        System.out.println("violenceMatch = " + violenceMatch);
    }

    /**
     * 暴力匹配算法
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 找到返回下标，没找到返回-1
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0; // i索引指向s1
        int j = 0; // j索引指向s2

        while (i < s1Len && j < s2Len) { // 保证匹配时，不越界
            if (s1[i] == s2[j]) { // 匹配成功
                i++;
                j++;
            } else { // 没有匹配成功
                // 如果失配(即str1[i]!=str2[j]),令i=i-(j-1),j=0
                i = i - (j - 1);
                j = 0;
            }
        }
        // 判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
