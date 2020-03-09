package ru.stqa.ptf.addressbook.model;

public class GroupContact {
    private  ContactData contact;
    private  GroupData group;

    public GroupContact(ContactData contact, GroupData group) {
        this.contact = contact;
        this.group = group;
    }

    public GroupContact() {

    }

    public ContactData getContact() {
        return contact;
    }

    public GroupData getGroup() {
        return group;
    }
    public GroupContact withGroupInContact(GroupData group) {
        this.group = group;
        return this;
    }

    public GroupContact withContactInGroup(ContactData contact) {
        this.contact = contact;
        return this;
    }
}
