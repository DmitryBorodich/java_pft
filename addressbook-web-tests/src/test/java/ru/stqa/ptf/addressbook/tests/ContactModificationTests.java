package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondirions() {
        app.сontact().gotoHomePage();
        if (app.сontact().all().size() == 0){
            app.сontact().create(new ContactData().withFirstname("jon1").withLastname("smit1").withMobile("+79999999991").withEmail("2@2.2").withAddress("test"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.сontact().all();
        ContactData modifitedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifitedContact.getId()).withFirstname("jon1").withLastname("smit1").withMobile("+79999999991").withEmail("2@2.2").withAddress("test");
        app.сontact().modify(contact);
        Contacts after = app.сontact().all();

        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifitedContact).withAdded(contact)));
    }


}
