package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    Set<ContactData> before = app.contact().all(); // список контактов до добавления нового контакта
    ContactData contact = new ContactData()
            .withFirst_name("John")
            .withLast_name("Doe")
            .withPhone_number("+375291111111")
            .withEmail("dummyemail@gmail.com")
            .withGroup_name("test1");
    app.contact().create(contact);
    //app.getNavigationHelper().goToHomePageInHeader();
    Set<ContactData> after = app.contact().all(); // список контактов после добавления нового контакта

    Assert.assertEquals(after.size(), before.size() + 1);


    //contact.setId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());


    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()); //вычисляется макисмальный идентификатор
    before.add(contact);


    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }

}
