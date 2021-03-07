package ru.stqa.pft.addressbook.tests;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
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
