package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() throws Exception {

    app.getNavigationHelper().returnToHomePage();
    if (! app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData("First name", "Last name", "+375290000000", "dummyemail@gmail.com", "test1"));
    }
    app.getContactHelper().selectContactCheckbox();
    app.getContactHelper().deleteContactMainPage();
    app.getContactHelper().closeAlert();

  }

}
