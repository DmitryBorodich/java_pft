package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;


public class ContactDeletionTests extends TestBase {
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Test
    public void testContactDeletion() throws Exception {
        app.getContactHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("jon1", "smit1", "+79999999991", "2@2.2", "test"));
        }
        app.getContactHelper().selectContact();
        acceptNextAlert = true;
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeAlertDeletedContact();
    }


}
