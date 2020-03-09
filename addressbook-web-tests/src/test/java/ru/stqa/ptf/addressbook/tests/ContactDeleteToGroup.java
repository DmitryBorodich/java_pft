package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteToGroup extends TestBase  {
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
    public void testContactDeleteToGroup() {
        Contacts allContacts = app.db().contacts();
        Groups allGroups = app.db().groups();
        GroupContact groupWithContact = groupWithContacts(allContacts, allGroups);
        GroupData group = groupWithContact.getGroup();
        Contacts contacts = group.getContacts();
        ContactData deletedContact = groupWithContact.getContact();

        app.сontact().gotoHomePage();
        app.сontact().deleteToGroup(deletedContact,group.getId());

        Contacts afterDeletionContacts = app.db().getGroupsFromDb(group.getId()).getContacts();
        assertThat(afterDeletionContacts, equalTo(contacts.without(deletedContact)));
    }

    private GroupContact groupWithContacts (Contacts allContacts, Groups allGroups) {
        for (ContactData contact : allContacts) {
            if (contact.getGroups().size() > 0) {
                GroupData group =  contact.getGroups().iterator().next();
                return new GroupContact().withGroupInContact(group).withContactInGroup(contact);
            }
        }
        GroupData group = allGroups.iterator().next();
        ContactData contact = allContacts.iterator().next();
        app.сontact().gotoHomePage();
        app.сontact().addToGroup(contact, group);
        app.сontact().getCurrentGroupPage(group);
        return new GroupContact().withGroupInContact(group).withContactInGroup(contact);
    }
}
