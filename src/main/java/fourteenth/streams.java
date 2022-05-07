package fourteenth;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

@Slf4j
@Test
public class streams {
    /**
     * 流（Streams）是与任何特定存储机制无关的元素序列
     */
    @Test
    void t1() {
        new Random(47)//seed 随机种子 多次运行产生随机数唯一
                .ints(5, 20)//to get more -》alt+7
                .distinct()//去重
                .limit(7)//
                .sorted()//排序 TODO
                .forEach(System.out::println);//方法引用
    }

    @Test
    void t2() {
        Stream.of("1", "2")
                .forEach(System.out::println);
        int sum = Stream.of("1", "2").mapToInt(i -> i.charAt(0)).sum();//对象流转换基本数据流 mapToInt。。。。
        Set<String> w =
                new HashSet<>(Arrays.asList("It's a wonderful day for pie!".split(" ")));// split() TODO 传入正则表达式操作文本 ，功能复杂
        w.stream()
                .map(x -> x + " ")
                .forEach(System.out::print);
        Map<String, Double> m = new HashMap<>();
        m.put("pi", 3.14159);
        m.put("e", 2.718);
        m.put("phi", 1.618);
        m.entrySet().stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .forEach(System.out::println);

    }

    /**
     * @throws IOException
     * @FunctionalInterface Supplier<String>
     * <p>
     * Stream.generate() 把任意Supplier<T> 用于生成 T 类型的流。
     */
    @Test
    void t3() throws IOException {
        class RandomWords implements Supplier<String> {
            List<String> words = new ArrayList<>();
            Random rand = new Random(47);

            RandomWords(String fname) throws IOException {
                List<String> lines = Files.readAllLines(Paths.get(fname));
// 略过第一行
                for (String line : lines.subList(1, lines.size())) {
                    for (String word : line.split("[ .?,]+"))
                        words.add(word.toLowerCase());
                }
            }

            @Override
            public String get() {
                return words.get(rand.nextInt(words.size()));
            }

        }

        System.out.println(
                Stream.generate(new RandomWords("src/main/resources/Cheese.dat"))
                        .limit(10)
                        .collect(Collectors.joining(" ")));


        List<String> words = new ArrayList<>();
        Random rand = new Random(47);
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/Cheese.dat"));
// 略过第一行
        for (String line : lines.subList(1, lines.size())) {
            for (String word : line.split("[ .?,]+"))
                words.add(word.toLowerCase());
        }
        List<String> wordss = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            wordss.add(
                    words.get(
                            rand.nextInt(words.size())
                    )
            );
        }

        String collect = words.stream().limit(10).collect(Collectors.joining(" "));
        System.out.println((words.stream().limit(10).collect(Collectors.joining(" "))));


    }

    @Test
    void t4() {
        repeat(3, () -> System.out.println("Looping!"));
    }

    void repeat(int n, Runnable action) {
        range(0, n).forEach(i -> action.run());
    }

    @Test
    void t5() {
        /**
         * iterate() 只能
         * 记忆结果，因此我们需要利用一个变量 x 追踪另外一个元素
         */
        class Fibonacci {
            int x = 1;
            Stream<Integer> numbers() {
                return Stream.iterate(0, i -> {
                    int result = x + i;
                    x = i;
                    return result;
                });
            }
        }
        new Fibonacci().numbers()
                .skip(20) // 过滤前 20 个
                .limit(10) // 然后取 10 个
                .forEach(System.out::println);
    }


    void t6() throws Exception {
        class FileToWordsBuilder {
            Stream.Builder<String> builder = Stream.builder();
            public FileToWordsBuilder(String filePath) throws Exception {
                Files.lines(Paths.get(filePath))
                        .skip(1) // 略过开头的注释行
                        .forEach(line -> {
                            for (String w : line.split("[ .?,]+"))
                                builder.add(w);
                        });
            }

            Stream<String> stream() {
                return builder.build();
            }
        }


        new FileToWordsBuilder("Cheese.dat")
                .stream()
                .limit(7)
                .map(w -> w + " ").forEach(System.out::println);

    }

}


