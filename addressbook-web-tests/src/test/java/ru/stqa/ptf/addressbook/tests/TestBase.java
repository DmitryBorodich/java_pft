package ru.stqa.ptf.addressbook.tests;


import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.ptf.addressbook.appmanager.ApplicationManager;


public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
    protected WebDriver wd;
    protected boolean acceptNextAlert = true;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }
    public String closeAlertAndGetItsText() {
        try {
            Alert alert = wd.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}
