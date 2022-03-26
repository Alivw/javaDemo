package com.jalivv.maven.wordcount;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Test {

    public static void main(String[] args) {
        demo(
                //()->new HashMap<String, Integer>(),
                () -> new ConcurrentHashMap<String,Integer>(),
                (map, words) -> {
                    for (String word : words) {
                        /**
                         * 使用 ConcurrentHashMap 同样达不到预期的效果
                         * chm虽然每个方法是原子的，但是下面3个方法组合起来就不是原子性的了
                         */
                        Integer counter = map.get(word);
                        int newVal = counter == null ? 1 : counter + 1;
                        map.put(word, newVal);
                    }
                }
        );

        demo(
                () -> new ConcurrentHashMap<String, LongAdder>(),
                (map, words) -> {
                    for (String word : words) {
                        // 如果缺少一个 key ，则计算生成一个 value ，然后将 key ，value 放入map
                        LongAdder longAdder = map.computeIfAbsent(word, (key) -> new LongAdder());
                        // 执行累加
                        longAdder.increment();
                    }
                }
        );


    }


    private static <V> void demo(Supplier<Map<String, V>> supplier, BiConsumer<Map<String, V>, List<String>> consumer) {
        Map<String, V> counterMap = supplier.get();
        List<Thread> ts = new ArrayList<>();
        for (int i = 1; i <= 26; i++) {
            int idx = i;
            Thread thread = new Thread(() -> {
                List<String> words = readFromFile(idx);
                consumer.accept(counterMap, words);
            });
            ts.add(thread);

        }

        ts.forEach(t -> t.start());

        ts.forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(counterMap);

    }

    private static List<String> readFromFile(int idx) {
        List<String> res = new ArrayList<>();
        try (InputStreamReader in = new InputStreamReader(new FileInputStream("MavenProjects/temp/" + idx + ".txt"))) {
            int temp;
            while ((temp = in.read()) != -1) {
                if (((char)temp)!='\n') {
                    res.add(String.valueOf((char) temp));
                }
            }
        } catch (IOException e) {
        }
        return res;
    }
}
