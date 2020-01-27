package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionTests extends TestBase {
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Test
    public void testContactDeletion() throws Exception {
        app.getContactHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("jon1", "smit1", "+79999999991", "2@2.2", "test"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.get(before.size() - 1).getId());
        acceptNextAlert = true;
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeAlertDeletedContact();
        app.getContactHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }


}
