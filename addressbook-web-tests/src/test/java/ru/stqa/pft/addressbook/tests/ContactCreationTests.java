package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();

    BufferedReader reader =
            new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null ) {
      String[] split = line.split(";");
      list.add(new Object[] {new ContactData()
              .withFirst_name(split[0])
              .withLast_name(split[1])
              .withPhone_number(split[2])
              .withEmail(split[3])
              .withGroup_name(split[4])});
      line = reader.readLine();
    }
    return list.iterator();
  }
  /*.withPhoto(new File("src/test/resources/stru.png")*/
  @Test (dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {

    app.goTo().HomePageInHeader();
    Contacts before = app.contact().all(); // список контактов до добавления нового контакта
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    //app.getNavigationHelper().goToHomePageInHeader();
    Contacts after = app.contact().all(); // список контактов после добавления нового контакта
    //contact.setId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }

}
