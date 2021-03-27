package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {

    /*added a check for a group to be created in application before contact creation
 because contact is waiting for at least 1 group*/

  app.goTo().GroupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test1"));}

  }

  @Test (enabled = true)
  public void testContactCreation() throws Exception {



    app.goTo().HomePageInHeader();
    List<ContactData> before = app.contact().list(); // список контактов до добавления нового контакта
    ContactData contact = new ContactData()
            .withFirst_name("John")
            .withLast_name("Doe")
            .withPhone_number("+375291111111")
            .withEmail("dummyemail@gmail.com")
            .withGroup_name("test1");
    app.contact().create(contact);
    //app.getNavigationHelper().goToHomePageInHeader();
    List<ContactData> after = app.contact().list(); // список контактов после добавления нового контакта

    Assert.assertEquals(after.size(), before.size() + 1);


    //contact.setId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());


    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }

}
