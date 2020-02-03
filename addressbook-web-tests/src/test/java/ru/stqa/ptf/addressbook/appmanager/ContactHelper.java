package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void backtoHomePage() {
        click(By.linkText("home page"));
    }

    public void fillContactForm(ContactData contactData) {
        type (By.name("firstname"), contactData.getFirstname());
        type (By.name("lastname"), contactData.getLastname());
        type (By.name("mobile"), contactData.getMobile());
        type (By.name("email"), contactData.getEmail());
        type (By.name("address2"), contactData.getAddress());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void gotoHomePage() {
        wd.get("http://localhost/addressbook/index.php");
    }

    public void selectContact(int index) {
        click(By.id(String.valueOf(index)));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void editContact(int number) {
        click(By.xpath("(//img[@alt='Edit'])[" + number  + "]"));
    }

    public void submitContactUpdate() {
        click(By.name("update"));
    }

    public void submitContactCreate() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void closeAlertDeletedContact() {
        wd.switchTo().alert().accept();
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreate();
        backtoHomePage();
    }

    public boolean isThereAContact() {
        return isElenentPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> contactEntryList = element.findElements(By.cssSelector("td"));
            WebElement firstname1 = contactEntryList.get(2);
            String firstname = firstname1.getText();
            WebElement lastname1 = contactEntryList.get(1);
            String lastname = lastname1.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            ContactData contact = new ContactData(id, firstname, lastname,null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
