package test;

/**
 * 缓存命中，当一个数据被访问时，邻近的数据会被加载到缓存中，另外寻址时间也会变长
 * @author mao  2020/1/8 6:21
 */
public class TestCacheHit {
    public static void main(String[] args) {
        long start = System.nanoTime();
        int[][] src = new int[2048][2048];
        for (int i = 0; i < 2048; i++) {
            for (int j = 0; j < 2048; j++) {
                src[j][i] = 1;
//                src[i][j] = 1;
            }
        }
        System.out.println(System.nanoTime() - start);  // src[i][j]12626800  src[j][i] 54161800  4倍差距

        int[][] dst = new int[2048][2048];
        long start2 = System.nanoTime();
//        copyij(src, dst);
        copyji(src, dst);
        System.out.println(System.nanoTime() - start2);  // copyij 7324500  copyji 93269500   10倍差距
    }
    static void copyij(int[][] src, int[][] dst) {
        for (int i = 0; i < 2048; i++) {
            for (int j = 0; j < 2048; j++) {
                dst[i][j] = src[i][j];
            }
        }
    }

    static void copyji(int[][] src, int[][] dst) {
        for (int j = 0; j < 2048; j++) {
            for (int i = 0; i < 2048; i++) {
                dst[i][j] = src[i][j];
            }
        }
    }
}
