package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    int before = app.getContactHelper().getContactCount(); // количество контактов до добавления нового контакта
    app.getContactHelper().createContact(new ContactData("First name", "Last name", "+375290000000", "dummyemail@gmail.com", "test1"));
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getContactCount(); // количество контактов после добавления нового контакта

    Assert.assertEquals(after, before + 1);
  }

}
