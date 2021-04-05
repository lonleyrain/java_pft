package ru.stqa.pft.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
  public Iterator<Object[]> validContactsFromXML() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null ) {
        xml+=line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>)xstream.fromXML(xml);
      return contacts.stream().map((c)-> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJSON() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null ) {
        json+=line;
        line = reader.readLine();
      }
      JsonDeserializer<File> deserializer = (json1, typeOfT, context) -> new File(json1.getAsJsonPrimitive().getAsString());
      Gson gson = new GsonBuilder().registerTypeAdapter(File.class, deserializer).create();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); //List<ContactData.class
      return contacts.stream().map((c)-> new Object[] {c}).collect(Collectors.toList()).iterator();

    }
  }

  /*.withPhoto(new File("src/test/resources/stru.png")*/
  @Test (dataProvider = "validContactsFromJSON")
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
