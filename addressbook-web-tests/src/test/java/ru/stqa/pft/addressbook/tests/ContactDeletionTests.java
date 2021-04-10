package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {


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
              .withEmail("dummyemail@gmail.com")
              .withGroup_name("test1"));
    }

  }


  @Test (enabled = true)

  public void testContactDeletion() throws Exception {


    app.goTo().HomePageInHeader();
    Contacts before = app.db().contacts(); // список контактов до удаления  контакта
    ContactData deletedContact = before.iterator().next(); // обращаемся к множеству через итератор и используем метод next чтобы вернуть первый попавшийся элемент множества
    app.contact().delete(deletedContact);
    app.goTo().HomePageInHeader();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts(); // список контактов после удаления  контакта
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();



  }


}
