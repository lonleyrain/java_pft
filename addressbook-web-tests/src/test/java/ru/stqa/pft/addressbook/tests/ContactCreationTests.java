package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().initNewContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("First name", "Last name", "+375290000000", "dummyemail@gmail.com", "test1"), true);
    app.getContactHelper().submitContactForm();
    app.getNavigationHelper().returnToHomePage();
  }

}
