package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By lokator) {
        wd.findElement(lokator).click();
    }

    protected void type(By lokator, String text) {
        click(lokator);
        if (text != null) {
            wd.findElement(lokator).clear();
            wd.findElement(lokator).sendKeys(text);
        } 
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
