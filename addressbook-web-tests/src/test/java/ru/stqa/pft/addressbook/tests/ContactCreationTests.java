package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().goToHomePageInHeader();
    List<ContactData> before = app.getContactHelper().getContactList(); // список контактов до добавления нового контакта
    app.getContactHelper().createContact(new ContactData("John", "Doe", "+375291111111", "dummyemail@gmail.com", "test1"));
    //app.getNavigationHelper().goToHomePageInHeader();
    List<ContactData> after = app.getContactHelper().getContactList(); // список контактов после добавления нового контакта

    Assert.assertEquals(after.size(), before.size() + 1);

  }

}
