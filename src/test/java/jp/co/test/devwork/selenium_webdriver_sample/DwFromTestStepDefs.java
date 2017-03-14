package jp.co.test.devwork.selenium_webdriver_sample;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import jp.co.test.devwork.selenium_webdriver_sample.dto.DwFromDto;
import jp.co.test.devwork.selenium_webdriver_sample.enums.Sex;

public class DwFromTestStepDefs {

    WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        baseUrl = "http://devlop-working.org";
        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chrome);
    }

    @When("^DWサンプルフォームにアクセスします。$")
    public void dwサンプルフォームにアクセスします() throws Throwable {
        driver.get(baseUrl + "/bookmarklet/web/dw/sample-form.php");
    }

    @Then("^メッセージラベルを確認する。\"([^\"]*)\"$")
    public void メッセージラベルを確認する(String msg) throws Throwable {
        assertEquals(msg, driver.findElement(By.cssSelector("p")).getText());
    }

    @When("^フォームの項目を入力します。$")
    public void フォームの項目を入力します(List<DwFromDto> DwFromDtoList) throws Throwable {

        DwFromDto dwFromDto = DwFromDtoList.get(0);

        // 氏名
        driver.findElement(By.id("simei")).clear();
        driver.findElement(By.id("simei")).sendKeys(dwFromDto.get氏名());

        // 生年月日（例：1985年1月23日）
        final int idx_year = dwFromDto.get生年月日().indexOf("年");
        final int idx_month = dwFromDto.get生年月日().indexOf("月");
        final int idx_day = dwFromDto.get生年月日().indexOf("日");
        final String year = dwFromDto.get生年月日().substring(0, idx_year);
        final String month = dwFromDto.get生年月日().substring(idx_year + 1, idx_month);
        final String day = dwFromDto.get生年月日().substring(idx_month + 1, idx_day);
        new Select(driver.findElement(By.name("birth_year"))).selectByVisibleText(year);
        new Select(driver.findElement(By.name("month"))).selectByVisibleText(month);
        new Select(driver.findElement(By.name("day"))).selectByVisibleText(day);

        // 性別（例：男）
        driver.findElement(By.id(Sex.getValueFromLabel(dwFromDto.get性別()).getValue())).click();

        // 希望日
        driver.findElement(By.id("dlv_date")).clear();
        driver.findElement(By.id("dlv_date")).sendKeys(dwFromDto.get希望日());
    }

    @When("^確認画面に遷移する。$")
    public void 確認画面に遷移する() throws Throwable {
        driver.findElement(By.cssSelector("button.execute")).click();
    }

    @Then("^確認画面の表示を確認します。$")
    public void 確認画面の表示を確認します(List<DwFromDto> DwFromDtoList) throws Throwable {
        // TODO 今回は実装しない
    }

    @When("^完了画面に遷移する。$")
    public void 完了画面に遷移する() throws Throwable {
        driver.findElement(By.cssSelector("button.execute")).click();

    }

    @Then("^登録履歴の表示を確認します。$")
    public void 登録履歴の表示を確認します(List<DwFromDto> DwFromDtoList) throws Throwable {

        for (int i = 0; i < DwFromDtoList.size(); i++) {
            int rowNum = i + 2; // 最初の試行が2行目から開始するようにする。

            DwFromDto dwFromDto = DwFromDtoList.get(i);

            assertEquals(dwFromDto.get氏名(), driver.findElement(By.xpath("//tr["+ rowNum +"]/td[1]")).getText());
            assertEquals(dwFromDto.get生年月日(), driver.findElement(By.xpath("//tr["+ rowNum +"]/td[2]")).getText());
            assertEquals(dwFromDto.get性別(), driver.findElement(By.xpath("//tr["+ rowNum +"]/td[3]")).getText());
            assertEquals(dwFromDto.get希望日(), driver.findElement(By.xpath("//tr["+ rowNum +"]/td[4]")).getText());
        }

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
