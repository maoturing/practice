package random;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * https://mp.weixin.qq.com/s/JwZ2GusqD9HZdN5Izbrd0w
 * https://stackoverflow.com/questions/15182496/why-does-this-code-using-random-strings-print-hello-world
 *
 * @author mao  2022/11/20
 */
public class SeedDemo {


    /**
     * 相同的seed的随机数实例，会生成相同的随机数序列
     * 下面的代码正是利用了这一特点，生成的随机数范围是[0, 27]
     * 然后随机数再加上 96, 转换为ascii码就可以得到hello world
     */
    public static void main(String[] args) {
        System.out.println(randomString(-229985452) + " " + randomString(-147909649));
        System.out.println("==========================");

        long[] seeds = getSeeds();

        for (long seed : seeds) {
            System.out.print(randomString(seed) + " ");
        }
    }

    private static long[] getSeeds() {
        long[] seeds = {0, 0, 0};
        seeds[0] = generateSeed("i");
        seeds[1] = generateSeed("love");
        seeds [2] = generateSeed("you");
        return seeds;
    }

    /**
     * 8  + 96 = 104 --> h
     * 5  + 96 = 101 --> e
     * 12 + 96 = 108 --> l
     * 12 + 96 = 108 --> l
     * 15 + 96 = 111 --> o
     * <p>
     * 23 + 96 = 119 --> w
     * 15 + 96 = 111 --> o
     * 18 + 96 = 114 --> r
     * 12 + 96 = 108 --> l
     * 4  + 96 = 100 --> d
     */
    public static String randomString(long i) {
        Random ran = new Random(i);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int k = ran.nextInt(27);
            if (k == 0) {
                break;
            }

            sb.append((char) ('`' + k));
        }
        return sb.toString();
    }

    /**
     * 生成指定字符串的随机数种子
     *
     * @param goal 目标字符串
     * @return 能生成目标字符串的随机数种子
     */
    public static long generateSeed(String goal) {
        char[] input = goal.toCharArray();
        char[] pool = new char[input.length];

        long start = Integer.MIN_VALUE;
        long finish = Integer.MAX_VALUE;

        label:
        // 遍历每一个种子
        for (long seed = start; seed < finish; seed++) {
            Random random = new Random(seed);

            // 生成随机字符
            for (int i = 0; i < input.length; i++)
                pool[i] = (char) (random.nextInt(27) + '`');

            if (random.nextInt(27) == 0) {
                for (int i = 0; i < input.length; i++) {
                    // 如果生成的随机字符与目标字符串不相同, 则跳过, 尝试下一个种子
                    if (input[i] != pool[i])
                        continue label;
                }
                System.out.println("seed: " + seed);
                return seed;
            }
        }
        throw new NoSuchElementException("Sorry :/");
    }
}
