package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactDeletionTests extends TestBase {
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeMethod
    public void ensurePrecondirions() {
        app.сontact().gotoHomePage();
        if (app.сontact().all().size() == 0){
            app.сontact().create(new ContactData().withFirstname("jon1").withLastname("smit1").withMobile("+79999999991").withEmail("2@2.2").withAddress("test"));
        }
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.сontact().all();
        ContactData deletionContact = before.iterator().next();
        app.сontact().delete(deletionContact);
        Contacts after = app.сontact().all();

        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletionContact)));
    }

}
