package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    initNewContactCreation();
    fillContactForm(new ContactData("First name", "Last name", "+375290000000", "dummyemail@gmail.com", "test1"));
    submitContactForm();
    returnToHomePage();
  }

}
