package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {

    /*added a check for a group to be created in application before contact modification
    because contact is waiting for at least 1 group to be present in app*/

    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));

    }

    app.goTo().HomePageInHeader();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirst_name("First name")
              .withLast_name("Last name")
              .withPhone_number("+375290000000")
              .withEmail("dummyemail@gmail.com")
              .withGroup_name("test1"));
    }

  }

  @Test

  public void testContactPhones() {
    app.goTo().HomePageInHeader();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }
}
