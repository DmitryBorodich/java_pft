package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase  {

  @Test
  public void testContactCreation() {
    app.сontact().gotoHomePage();
    Contacts before = app.сontact().all();
    File photo = new File("src/test/resources/stru.png");
    ContactData contact = new ContactData().withFirstname("jon1").withLastname("smit1").withEmail("2@2.2").withAddress("test")
            .withHomePhone("+79999999991").withMobile("+79999999992").withWorkPhone("+79999999993")
            .withPhoto(photo);
    app.сontact().create(contact);
    Contacts after = app.сontact().all();

    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }


}
