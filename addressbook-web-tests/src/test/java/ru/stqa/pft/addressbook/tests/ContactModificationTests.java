package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() throws Exception {

    int before = app.getContactHelper().getContactCount(); // количество контактов до модификации контакта

    if (! app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData("First name", "Last name", "+375290000000", "dummyemail@gmail.com", "test1"));
    }

    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("First name", "Last name", "+375290000000", "dummyemail@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();

    int after = app.getContactHelper().getContactCount(); // количество контактов после модификации контакта

    Assert.assertEquals(after, before);


  }


}
