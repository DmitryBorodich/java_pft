package ru.stqa.ptf.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    returnToGroupPage("groups");
    submitGroupCreation("new");
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation("submit");
    returnToGroupPage("group page");
  }

}
