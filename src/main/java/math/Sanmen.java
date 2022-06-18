package math;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

/**
 * 三门问题
 *
 * @author mao  2022/6/18
 */
@Slf4j
public class Sanmen {
    private static final int GIFT_DOOR = 1;
    private Random random = new Random();

    public static void main(String[] args) {
        // 获得奖品的次数
        int count = 0;

        for (int i = 0; i < 10000; i++) {
            Sanmen sanmen = new Sanmen();
            if (sanmen.run()) {
                count++;
            }
        }

        log.info("10000次模拟，每次选手都选择换门，换门后获奖次数:" + count);
        log.info("换门后获奖概率: " + count/10000.0);
    }

    public boolean run() {
        Sanmen sanmen = new Sanmen();
        int[] threeDoor = sanmen.getThreeDoor();
        int giftIndex = getGiftIndex(threeDoor);
        // 选手重新选择的门
        int newChoice = -1;

        // 选手随机选取一扇门
        int choiceIndex = random.nextInt(3);

        // 选手猜对了
        if (threeDoor[choiceIndex] == GIFT_DOOR) {
            // 主持人在剩下两扇门随机打开一扇门, 选手选择换门
            newChoice = getNewChoice(choiceIndex);

        } else {// 选手猜错了,
            // 主持人只能打开选手没选的两扇门中的空门
            // 选手交换选择, 也只能交换到有礼物的门
            newChoice = giftIndex;
        }

        if (threeDoor[newChoice] == GIFT_DOOR) {
            log.info("恭喜你, 换门后获得了奖品!");
            return true;
        } else {
            log.info("很抱歉, 换门后未获得奖品!");
            return false;
        }
    }

    /**
     * 生成三门, 0表示没礼物, 1表示有礼物
     * @return
     */
    private int[] getThreeDoor() {
        // 三扇门, 0表示没礼物, 1表示有礼物
        int[] result = {0, 0, 0};

        // 随机选取一扇门, 放置礼物
        int giftIndex = random.nextInt(3);
        result[giftIndex] = GIFT_DOOR;

        log.info("ThreeDoor: " + Arrays.toString(result));
        return result;
    }

    //
    private int getNewChoice(int choiceIndex) {
        // true表示靠左的门, false表示靠右的门
        boolean direction = random.nextBoolean();
        int newChoice = -1;
        if (choiceIndex == 0) {
            newChoice = direction ? 1 : 2;
        } else if (choiceIndex == 1) {
            newChoice = direction ? 0 : 2;
        } else if (choiceIndex == 2) {
            newChoice = direction ? 0 : 1;
        }
        return newChoice;
    }

    private int getGiftIndex(int[] threeDoor) {
        for (int i = 0; i < threeDoor.length; i++) {
            if (threeDoor[i] == 1) {
                return i;
            }
        }
        return -1;
    }


}
