package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase {
    @BeforeMethod
    public void ensurePrecondirions() {
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0){
            app.сontact().gotoHomePage();
            app.сontact().create(new ContactData().withFirstname("jon1").withLastname("smit1")
                    .withMobile("+79999999991").withEmail("2@2.2").withAddress("test")
                    .withInGroup(groups.iterator().next()));
        }
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testContactAddToGroup() {
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData addedContact = newContact(before);
        GroupData group = groups.iterator().next();
        app.сontact().gotoHomePage();
        app.сontact().addToGroup(addedContact, group);
        Groups afterAdditionContact = app.db().getContactFromDb(addedContact.getId()).getGroups();
        assertThat(afterAdditionContact, equalTo(addedContact.getGroups().withAdded(group)));
    }

    private ContactData newContact(Contacts before) {
        for (ContactData contact : before) {
            if (contact.getGroups().size() < app.db().groups().size()) {
                return contact;
            }
        }
        int nextId = app.getContactHelper().getNextId(before);
        app.getContactHelper().create(new ContactData().withFirstname("jon1").withLastname("smit1").withAddress("333").withId(nextId));
        ContactData newContact = app.db().contacts().getContactById(app.db().contacts(), nextId);
        return newContact;
    }

}
