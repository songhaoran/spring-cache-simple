package com.song.cache.common;

import java.util.*;

/**
 * Created by Song on 2017/9/28.
 */
public class CountPhone {
    /**
     * 计数基类
     */
    class SubCount {
        private HashMap<String, Integer> map;

        private List<String> list;

        private int flag;

        public SubCount(HashMap<String, Integer> map, List<String> list) {
            this.map = map;
            this.list = list;
            flag = 0;
        }

        public synchronized void count(int i) {
            for (int j = 0; j < 1000; j++) {
                System.out.println(Thread.currentThread().getName() + ":" + "i:"+i+":j:"+":"+(i + j));
                if (list.size() > (i + j) && map.containsKey(list.get(i + j))) {
                    map.put(list.get(i + j), map.get(list.get(i + j) + 1));
                } else {
                    System.out.println(i + ":" + (i + j));
                    map.put(list.get(i + j), 1);
                }
            }
            flag++;
        }

        public Integer getFlag() {
            return flag;
        }
    }


    class CountThread implements Runnable {
        private SubCount subCount;
        private Integer i;

        public CountThread(SubCount subCount, Integer i) {
            this.subCount = subCount;
            this.i = i;
        }

        @Override
        public void run() {
            while (true) {
                subCount.count(i);
            }
        }
    }

    public Map<String, Integer> getCountMap(List<String> phones) throws Exception {
        HashMap<String, Integer> map = new HashMap<>();
        SubCount subCount = new SubCount(map, phones);
        int flag = (new Double(Math.floor(phones.size() / 100))).intValue();

        for (int i = 0; i < phones.size(); i = i + 1000) {
            new Thread(new CountThread(subCount, i)).start();
        }

        while (true) {
            if (subCount.flag == flag) {
                return map;
            }
            Thread.sleep(100);
        }
    }


    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            list.add(arr[random.nextInt(9)]);
        }

        Map<String, Integer> countMap = new CountPhone().getCountMap(list);
        System.out.println(countMap);
    }

}
