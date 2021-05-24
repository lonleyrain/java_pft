package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {

    /*added a check for a group to be created in application before contact modification
    because contact is waiting for at least 1 group to be present in app*/

    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    app.goTo().HomePageInHeader();

    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirst_name("First name")
              .withLast_name("Last name")
              .withMobilePhone("+375290000000")
              .withEmail("dummyemail@gmail.com"));
      //.withGroup_name("test1"));
    }
  }

  @Test

  public void testContactAddToGroup() {

    app.goTo().HomePageInHeader();
    ContactData addContact = selectContact();
    GroupData groupContactToBeAddedTo = selectGroup(addContact);
    Groups before = addContact.getGroups();
    app.goTo().HomePageInHeader();
    app.contact().addContactToGroup(addContact, groupContactToBeAddedTo);
    app.goTo().HomePageInHeader();
    ContactData addContactAfter = selectContactById(addContact);
    Groups after = addContactAfter.getGroups();
    assertThat(after, equalTo(before.withAdded(groupContactToBeAddedTo)));
  }

  private ContactData selectContactById(ContactData addContact) {
    Contacts allContacts = app.db().contacts();
    int a = addContact.getId();
    for (ContactData r : allContacts) {
      if (a == r.getId()) {
        return r;
      }
    }
    return null;

  }

  private GroupData selectGroup(ContactData contact) {
    Groups allGroups = app.db().groups();
    Groups contactsInGroups = contact.getGroups();

    Collection<GroupData> contactGroups = new HashSet<>(contactsInGroups);
    Collection<GroupData> avaliableGroups = new HashSet<>(allGroups);
    avaliableGroups.removeAll(contactGroups);
    return avaliableGroups.iterator().next();
  }


  private ContactData selectContact() {

    Contacts allContacts = app.db().contacts();
    Groups allGroups = app.db().groups();
    for (ContactData contact : allContacts) {
      if (contact.getGroups().size() < allGroups.size()) {
        return contact;
      }
    }
    app.goTo().GroupPage();
    app.group().create(new GroupData().withName("group created by test").withHeader("header for group from test"));
    return allContacts.iterator().next();
  }
}



//Contacts before = app.db().contacts();
//ContactData contactToBeAddedToGroup = before.iterator().next(); // to change this method the way it selects a contact which does not have any group
//Groups beforeAddingNewContact = contactToBeAddedToGroup.getGroups();
//app.contact().addContactToGroup(contactToBeAddedToGroup);
//Groups afterAddingNewContact = contactToBeAddedToGroup.getGroups();
// app.goTo().HomePageInHeader();
