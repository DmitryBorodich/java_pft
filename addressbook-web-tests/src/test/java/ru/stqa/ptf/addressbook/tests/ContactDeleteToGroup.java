package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeleteToGroup extends TestBase  {
    @BeforeMethod
    public void ensurePrecondirions() {
        if (app.db().contacts().size() == 0){
            app.сontact().gotoHomePage();
            app.сontact().create(new ContactData().withFirstname("jon1").withLastname("smit1").withMobile("+79999999991").withEmail("2@2.2").withAddress("test"));
        }
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testContactDeleteToGroup() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();

        app.сontact().gotoHomePage();
        app.сontact().deleteToGroup(deletedContact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before));
    }
}
