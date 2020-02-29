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
        if (app.db().contacts().size() == 0){
            app.сontact().gotoHomePage();
            app.сontact().create(new ContactData().withFirstname("jon1").withLastname("smit1").withMobile("+79999999991").withEmail("2@2.2").withAddress("test"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifitedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifitedContact.getId()).withFirstname("jon1").withLastname("smit1")
                .withMobile("+79999999991").withWorkPhone("+79999999992").withHomePhone("+79999999993")
                .withEmail("2@2.2").withEmail2("2@2.2").withEmail3("3@3.3")
                .withAddress("test");
        app.сontact().gotoHomePage();
        app.сontact().modify(contact);
        Contacts after = app.db().contacts();

        assertEquals(after.size(), before.size());
        Contacts beforeChance = before.without(modifitedContact).withAdded(contact);
        assertThat(after, equalTo(beforeChance));
    }


}
