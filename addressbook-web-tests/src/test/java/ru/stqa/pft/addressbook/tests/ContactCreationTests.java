package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;



import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {

    /*added a check for a group to be created in application before contact creation
 because contact is waiting for at least 1 group*/

  app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));}

  }

  @Test (enabled = true)
  public void testContactCreation() throws Exception {



    app.goTo().HomePageInHeader();
    Contacts before = app.contact().all(); // список контактов до добавления нового контакта
    ContactData contact = new ContactData()
            .withFirst_name("John")
            .withLast_name("Doe")
            .withPhone_number("+375291111111")
            .withEmail("dummyemail@gmail.com")
            .withGroup_name("test1");
    app.contact().create(contact);
    //app.getNavigationHelper().goToHomePageInHeader();
    Contacts after = app.contact().all(); // список контактов после добавления нового контакта

    assertThat(after.size(), equalTo(before.size() + 1));


    //contact.setId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());


    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }

}
