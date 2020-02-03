package ru.stqa.ptf.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> deligate;


    public Contacts(Contacts contacts) {
        this.deligate = new HashSet<ContactData>(contacts.deligate);
    }

    public Contacts() {
        this.deligate = new HashSet<ContactData>();
    }

    @Override
    protected Set<ContactData> delegate() {
        return null;
    }

    public Contacts withAdded(ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }
    public Contacts without(ContactData contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
