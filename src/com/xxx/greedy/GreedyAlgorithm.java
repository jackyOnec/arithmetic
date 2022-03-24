package com.xxx.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 创建广播电台，放入到Map
        HashMap<String, HashSet<String>> broadCasts = new HashMap<>();

        // 将各个电台放入到broadCasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        // 加入到map
        broadCasts.put("K1", hashSet1);
        broadCasts.put("K2", hashSet2);
        broadCasts.put("K3", hashSet3);
        broadCasts.put("K4", hashSet4);
        broadCasts.put("K5", hashSet5);

        // 存放所有地区
        HashSet<String> allAreas = AllAreas(broadCasts);

        // 创建ArrayList 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        // 定义临时集合，在遍历的过程中电台覆盖地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        // 定义给maxKey，保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        // 如果maxKey 不为null,则会加入到selects
        String maxKey = null;

        while (allAreas.size() != 0) { // 如果allAreas 不为0，则表示还没有覆盖到所有的地区
            // 每次进行一次while，需要把maxKey清空
            maxKey = null;
            // 遍历broadCasts,取出对应的key
            for (String key : broadCasts.keySet()) {
                tempSet.clear();
                // 当前这个key能够覆盖的地区
                HashSet<String> areas = broadCasts.get(key);
                tempSet.addAll(areas);

                // 求出tempSet和allAreas集合的交集，交集会赋给tempSet
                tempSet.retainAll(allAreas);

                // 如果当前这个集合包含未覆盖地区的数量，比maxKey指向集合地区还多就需要重置maxKey
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadCasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }

            // maxKey != null 就应该将maxKey加入到selects
            if (maxKey != null) {
                selects.add(maxKey);
                // 将maxKey指向的广播电台覆盖的地区，从allAreas去掉
                allAreas.removeAll(broadCasts.get(maxKey));
            }
        }
        System.out.println("selects = " + selects);
    }

    /**
     * @param broadCasts 广播电台集合
     * @return 存放地区
     */
    public static HashSet<String> AllAreas(HashMap<String, HashSet<String>> broadCasts) {
        // 存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        broadCasts.values().forEach(allAreas::addAll);
        // 等同于
//        for (HashSet<String> value : broadCasts.values()) {
//            allAreas.addAll(value);
//        }
        return allAreas;
    }
}
