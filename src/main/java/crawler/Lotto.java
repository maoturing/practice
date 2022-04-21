package crawler;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 爬取 biliUrl 页面所有转发用户, 从中随机抽取一个用户
 *
 * 教程: https://www.bilibili.com/video/BV18E411P7Uu
 *
 * @author mao  2021/3/1 14:25
 */
// Java项目使用@Slf4j: 1.要引入lombok, 2.要引入log4j和转化包, 3.安装lombok idea插件, 4.配置log4j.properties文件
@Slf4j
public class Lotto {
    // chromedriver地址
    public static final String chormeDriver = "D:\\local\\chromedriver_win32\\chromedriver.exe";
    // 需要爬取的转发页面,tab=1是转发, tab=2是评论
    public static final String biliUrl = "https://t.bilibili.com/563616528050910167?tab=1";


/*
  <div data-v-78a08664="" class="item-user">
      <a data-v-78a08664="" href="//space.bilibili.com/10931556/dynamic" target="_blank" class="user-name c-pointer" data-userinfo-popup-inited="true">
          月月臭臭猪
      </a>
  </div>

  点击转发用户的昵称, 得到上面这段html, 我们可以全局查找 class="item-user", 获取属性 href 和text
 */

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", chormeDriver);
        WebDriver driver = new ChromeDriver();
        // 保存所有用户id
        Set<String> users = new HashSet<>();

        // 1. 打开页面
        driver.get(biliUrl);
        TimeUnit.SECONDS.sleep(10);

        // 2. 不断下拉页面
        scorllPage(driver);

        // 3. 获取所有转发用户
        List<WebElement> eles = driver.findElements(By.xpath("//*[@class=\"item-user\"]/a[@href]"));
        List<WebElement> eles2 = driver.findElements(By.className("item-user"));
        log.info("查找到转发总数: " + eles.size());

        // 抓取转发+评论的用户，并用Set数据结构去重（多次转发并不能增加中奖几率哦）
        // 保存用户主页url + 用户名
        for (WebElement ele : eles) {
            users.add(ele.getAttribute("href") + "\t" + ele.getText());
        }
        log.info("转发总人数: " + users.size());

        // 3. 随机抽取一个用户
        Random random = new Random();  // 从去重结果中随机抽取用户
        List<String> list = new ArrayList<>(users);
        System.out.println("恭喜B站用户：" + list.get(random.nextInt(users.size())));
    }

    private static void scorllPage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (true) {
            try {
                // 下拉页面
                js.executeScript("window.scrollBy(0,500000)");

                // 查找不到元素[没有更多了]会抛出异常, 继续循环, 如果查找到了执行break退出循环
                WebElement ele = driver.findElement(By.className("nomore"));
                break;
            } catch (Exception e) {
                log.info("存在更多转发, 继续下拉页面...");
            }
        }
    }
}