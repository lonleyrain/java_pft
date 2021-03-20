package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() throws Exception {

    if (! app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData("First name", "Last name", "+375290000000", "dummyemail@gmail.com", "test1"));
    }

    List<ContactData> before = app.getContactHelper().getContactList(); // список контактов до изменения контакта

    app.getContactHelper().initContactModification(before.size());
    app.getContactHelper().fillContactForm(new ContactData("First name", "Last name", "+375290000000", "dummyemail@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePage();

    List<ContactData> after = app.getContactHelper().getContactList(); // список контактов после изменения контакта

    Assert.assertEquals(after.size(), before.size());


  }


}
