package jp.co.test.devwork.selenium_webdriver_sample;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class AppTest {

    WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        baseUrl = "http://devlop-working.org";
        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chrome);
        driver.get("https://www.google.co.jp/");
    }

    @Test
    public void testCase() throws Exception {
        driver.get(baseUrl + "/bookmarklet/web/dw/sample-form.php");
        assertEquals("以下の項目を入力してください。", driver.findElement(By.cssSelector("p")).getText());
        driver.findElement(By.id("simei")).clear();
        driver.findElement(By.id("simei")).sendKeys("山田花子");
        new Select(driver.findElement(By.name("birth_year"))).selectByVisibleText("1990");
        new Select(driver.findElement(By.name("month"))).selectByVisibleText("1");
        new Select(driver.findElement(By.name("day"))).selectByVisibleText("23");
        driver.findElement(By.id("sex_female")).click();
        driver.findElement(By.id("dlv_date")).clear();
        driver.findElement(By.id("dlv_date")).sendKeys("2017/03/22");
        driver.findElement(By.cssSelector("button.execute")).click();
        assertEquals("以下の内容でよろしいですか。", driver.findElement(By.cssSelector("p")).getText());
        driver.findElement(By.cssSelector("button.execute")).click();
        assertEquals("ご登録ありがとうございました。", driver.findElement(By.cssSelector("p")).getText());
        driver.get(baseUrl + "/bookmarklet/web/dw/sample-form.php");
        assertEquals("以下の項目を入力してください。", driver.findElement(By.cssSelector("p")).getText());
        driver.findElement(By.id("simei")).clear();
        driver.findElement(By.id("simei")).sendKeys("山田太郎");
        new Select(driver.findElement(By.name("birth_year"))).selectByVisibleText("1985");
        new Select(driver.findElement(By.name("month"))).selectByVisibleText("2");
        new Select(driver.findElement(By.name("day"))).selectByVisibleText("15");
        driver.findElement(By.id("sex_male")).click();
        driver.findElement(By.id("simei")).clear();
        driver.findElement(By.id("simei")).sendKeys("2017/03/12");
        driver.findElement(By.cssSelector("button.execute")).click();
        assertEquals("以下の内容でよろしいですか。", driver.findElement(By.cssSelector("p")).getText());
        driver.findElement(By.cssSelector("button.execute")).click();
        assertEquals("ご登録ありがとうございました。", driver.findElement(By.cssSelector("p")).getText());
        assertEquals("山田太郎", driver.findElement(By.xpath("//tr[3]/td[1]")).getText());
        assertEquals("1980年12月6日", driver.findElement(By.xpath("//tr[3]/td[2]")).getText());
        assertEquals("男", driver.findElement(By.xpath("//tr[3]/td[3]")).getText());
        assertEquals("2017/01/22", driver.findElement(By.xpath("//tr[3]/td[4]")).getText());

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
