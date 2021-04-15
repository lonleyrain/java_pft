package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactRemoveFromGroupTests extends TestBase{

  @BeforeMethod

  public void ensurePreconditions() {

    /*added a check for a group to be created in application before contact modification
    because contact is waiting for at least 1 group to be present in app*/

    //app.goTo().GroupPage();
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    //app.goTo().HomePageInHeader();
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

  public void testContactRemoveFromGroup() {

    ContactData contact = selectContact();
    GroupData groupContactToRemovedFrom = selectGroup(contact);
    Groups before = contact.getGroups();
    app.goTo().HomePageInHeader();
    app.contact().selectGroupFromList(groupContactToRemovedFrom.getId());
    app.contact().removeContactFromGroup(contact, groupContactToRemovedFrom);

    ContactData contactsAfter = selectContactById(contact);
    Groups after = contactsAfter.getGroups();
    assertThat(after, equalTo(before.without(groupContactToRemovedFrom)));

  }

  private ContactData selectContactById(ContactData contact) {
    Contacts contactsById = app.db().contacts();
    return contactsById.iterator().next().withId(contact.getId());
  }


  private GroupData selectGroup(ContactData removeContact) {

    ContactData contact = selectContactById(removeContact);
    Groups removedContact = contact.getGroups();
    return removedContact.iterator().next();

  }

  private ContactData selectContact() {
    Contacts allContacts = app.db().contacts();
    for (ContactData contact : allContacts) {
      if (contact.getGroups().size() > 0) {
        return contact;
      }
    }

    ContactData addContact = app.db().contacts().iterator().next();
    app.contact().addContactToGroup(addContact, app.db().groups().iterator().next());
    return addContact;
  }

}


/*app.goTo().HomePageInHeader();
        //app.contact().selectGroupWithContact();
        //Contacts before = app.db().contactsInGroup();
        Contacts before = app.contact().all();
        ContactData contactRemovedFromGroup = before.iterator().next();
        app.contact().removeContactFromGroup(contactRemovedFromGroup);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        //Contacts after = app.db().contacts();
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(contactRemovedFromGroup)));
        verifyContactListInUI();*/
