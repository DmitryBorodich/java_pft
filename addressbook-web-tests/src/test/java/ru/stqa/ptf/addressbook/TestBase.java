package ru.stqa.ptf.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
     WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
      wd = new FirefoxDriver();
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/group.php");
      login("admin", "secret");
    }

    private void login(String username, String password) {
      initGroupCreation("user");
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.id("LoginForm")).click();
      wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    protected void returnToGroupPage(String s) {
      gotoGroupPage(s);
    }

    protected void submitGroupCreation(String submit) {
      initGroupCreation(submit);
    }

    protected void fillGroupForm(GroupData groupData) {
      initGroupCreation("group_name");
      wd.findElement(By.name("group_name")).clear();
      wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
      initGroupCreation("group_header");
      wd.findElement(By.name("group_header")).clear();
      wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      initGroupCreation("group_footer");
      wd.findElement(By.name("group_footer")).clear();
      wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    protected void initGroupCreation(String s) {
      wd.findElement(By.name(s)).click();
    }

    protected void gotoGroupPage(String groups) {
      wd.findElement(By.linkText(groups)).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
      wd.quit();
      }

    protected void deleteSelectedGroups() {
      wd.findElement(By.name("delete")).click();
    }

    protected void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
    }
}
