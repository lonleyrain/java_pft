package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

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

  public void testContactModification() throws Exception {

    app.goTo().HomePageInHeader();

    Contacts before = app.contact().all(); // список контактов до изменения контакта
    ContactData modifiedContact = before.iterator().next(); // обращаемся к множеству через итератор и используем метод next чтобы вернуть первый попавшийся элемент множества
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirst_name("First name")
            .withLast_name("Last name")
            .withPhone_number("+375290000000")
            .withEmail("dummyemail@gmail.com");

    app.contact().modify(contact);
    app.goTo().HomePageInHeader();
    Contacts after = app.contact().all(); // список контактов после изменения контакта
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));


  }



}
