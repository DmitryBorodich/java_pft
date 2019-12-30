package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() throws Exception {
        app.getContactHelper().gotoHomePage();
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("jon1", "smit1", "+79999999991", "2@2.2", "test"));
        app.getContactHelper().submitContactUpdate();
        app.getContactHelper().backtoHomePage();
    }

}
