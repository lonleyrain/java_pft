package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() throws Exception {

    app.getNavigationHelper().goToHomePage();
    int before = app.getContactHelper().getContactCount(); // количество контактов до удаления контакта
    if (! app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData("First name", "Last name", "+375290000000", "dummyemail@gmail.com", "test1"));
    }
    app.getContactHelper().selectContactCheckbox(before - 1);
    app.getContactHelper().deleteContactMainPage();
    app.getContactHelper().closeAlert();
    int after = app.getContactHelper().getContactCount(); // количество контактов после удаления контакта

    Assert.assertEquals(after, before - 1);

  }

}
