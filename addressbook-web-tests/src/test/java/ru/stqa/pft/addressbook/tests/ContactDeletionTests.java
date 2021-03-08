package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() throws Exception {

    //app.getContactHelper().initContactModification();
    //app.getContactHelper().deleteContactInEditMode();

    app.getContactHelper().selectContactCheckbox();
    app.getContactHelper().deleteContactMainPage();

  }

}
