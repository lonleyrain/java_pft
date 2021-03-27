package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {


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


  @Test (enabled = true)

  public void testContactDeletion() throws Exception {


    app.goTo().HomePageInHeader();

    Set<ContactData> before = app.contact().all(); // список контактов до удаления  контакта
    ContactData deletedContact = before.iterator().next(); // обращаемся к множеству через итератор и используем метод next чтобы вернуть первый попавшийся элемент множества

    app.contact().delete(deletedContact);
    app.goTo().HomePageInHeader();

    Set<ContactData> after = app.contact().all(); // список контактов после удаления  контакта

    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(deletedContact);
    Assert.assertEquals(before, after);


  }


}
