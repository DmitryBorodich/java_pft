package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase  {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().gotoHomePage();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("jon", "smit", "+79999999999", "2@2.2", "test"));
    app.getContactHelper().backtoHomePage();
  }
}
