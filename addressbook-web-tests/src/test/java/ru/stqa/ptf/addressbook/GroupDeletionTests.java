package ru.stqa.ptf.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() throws Exception {
    gotoGroupPage("groups");
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage("group page");
  }

}
