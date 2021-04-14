package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactRemoveFromGroupTests extends TestBase{

  @Test

  public void testContactRemoveFromGroup() {

    app.goTo().HomePageInHeader();
    app.contact().selectGroupWithContact();
    Contacts before = app.db().contactsInGroup();
    //Contacts before = app.contact().all();
    ContactData contactRemovedFromGroup = before.iterator().next();
    app.contact().removeContactFromGroup(contactRemovedFromGroup);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    //Contacts after = app.db().contacts();
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(contactRemovedFromGroup)));
    verifyContactListInUI();



  }
}
