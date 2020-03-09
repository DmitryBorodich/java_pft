package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    protected boolean acceptNextAlert = true;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void backtoHomePage() {
        click(By.linkText("home page"));
    }

    public void fillContactForm(ContactData contactData) {
        type (By.name("firstname"), contactData.getFirstname());
        type (By.name("lastname"), contactData.getLastname());
        type (By.name("mobile"), contactData.getMobilePhone());
        type (By.name("email"), contactData.getEmail());
        type (By.name("email2"), contactData.getEmail2());
        type (By.name("email3"), contactData.getEmail3());
        type (By.name("address"), contactData.getAddress());
        type (By.name("home"), contactData.getHomePhone());
        type (By.name("work"), contactData.getWorkPhone());
        attach (By.name("photo"), contactData.getPhoto());
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

  //  public void editContact(int number) { wd.findElements(By.xpath(String.format("//tr.[.//input[@value='%s']]"), number))).click(); }

    public void submitContactUpdate() {
        click(By.name("update"));
    }

    public void submitContactCreate() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void closeAlertDeletedContact() {
        wd.switchTo().alert().accept();
    }

    public void selectContactById(int id) { wd.findElement(By.cssSelector("input[value='" + id + "']")).click(); }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreate();
        backtoHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        submitContactUpdate();
        backtoHomePage();
    }

    public void delete(ContactData deletionContact) {
        selectContact(deletionContact.getId());
        acceptNextAlert = true;
        deleteSelectedContact();
        closeAlertDeletedContact();
        gotoHomePage();
    }

    public boolean isThereAContact() {
        return isElenentPresent(By.name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> contactEntryList = element.findElements(By.cssSelector("td"));
            WebElement firstname1 = contactEntryList.get(2);
            String firstname = firstname1.getText();
            WebElement lastname1 = contactEntryList.get(1);
            String lastname = lastname1.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String allPhones = contactEntryList.get(5).getText();
            String allEmailsAddress = contactEntryList.get(4).getText();
            String address = contactEntryList.get(3).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones).withAllEmails(allEmailsAddress).withAddress(address));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobile(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s'", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    public void addToGroup(ContactData addedContact, GroupData group) {
        selectContact(addedContact.getId());
        click(By.name("to_group"));
        click(By.cssSelector("select[name=\"to_group\"] > option[value=\""+group.getId()+"\"]"));
        click(By.name("add"));
        gotoHomePage();
    }

    public void deleteToGroup(ContactData deletedContact, int id) {
        click(By.name("group"));
        click(By.cssSelector("select[name=\"group\"] > option[value=\""+ id +"\"]"));
        selectContactById(deletedContact.getId());
        click(By.name("remove"));

    }

    public int getNextId(Contacts contacts) {
        int max = 0;
        for (ContactData contact : contacts) {
            if (contact.getId() > max) {
                max= contact.getId();
            }
        }
        return max+1;
    }

    public void getCurrentGroupPage(GroupData group) {
        click(By.linkText("group page \""+group.getName()+"\""));
    }
}
