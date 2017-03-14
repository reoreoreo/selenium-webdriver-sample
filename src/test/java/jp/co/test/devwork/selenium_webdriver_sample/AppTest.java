package jp.co.test.devwork.selenium_webdriver_sample;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AppTest {

    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chrome);
        driver.get("https://www.google.co.jp/");
    }

    @Test
    public void testCase() throws Exception {
        Thread.sleep(10000);

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
