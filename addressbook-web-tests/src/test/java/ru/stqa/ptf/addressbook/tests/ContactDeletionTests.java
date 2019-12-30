package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.*;


public class ContactDeletionTests extends TestBase {
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Test
    public void testContactDeletion() throws Exception {
        app.getContactHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        acceptNextAlert = true;
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().closeAlertDeletedContact();
    }


}
