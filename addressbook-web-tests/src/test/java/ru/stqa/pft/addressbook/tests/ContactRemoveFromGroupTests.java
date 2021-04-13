package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactRemoveFromGroupTests extends TestBase{

  @Test

  public void testContactRemoveFromGroup() {

    app.goTo().HomePageInHeader();
    app.contact().selectGroupWithContact();
    Contacts before = app.db().contacts();
    ContactData contactRemovedFromGroup = before.iterator().next();
    app.contact().removeContactFromGroup(contactRemovedFromGroup);



  }
}
