package time;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * Java8中新的时间api
 *
 * @author mao  11/19/2022
 */
public class InstantDemo {
    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        TimeUnit.SECONDS.sleep(2);
        Instant end =  Instant.now();

        System.out.println(Duration.between(start, end));
    }
}
