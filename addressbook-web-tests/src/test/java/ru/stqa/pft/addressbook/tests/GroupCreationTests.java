package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {



  @DataProvider
  public Iterator<Object[]> validGroupsFromXML() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null ) {
        xml+=line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>)xstream.fromXML(xml);
      return groups.stream().map((g)-> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJSON() throws IOException {
    try (BufferedReader reader =
                 new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null ) {
        json+=line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType()); //List<GroupData>.class
      return groups.stream().map((g)-> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }


  @Test (dataProvider = "validGroupsFromJSON")
  public void testGroupCreation(GroupData group) throws Exception {

    app.goTo().GroupPage();
    Groups before = app.db().groups(); // список групп до добавления новой группы, взятый из базы данных
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() +1));
    app.goTo().GroupPage();
    Groups after = app.db().groups(); // список групп после добавления новой группы, взятый из базы данных
    // group.setId(after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()); вычисляется максимальный айди
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }


  @Test (enabled = false)
  public void testBadGroupCreation() throws Exception {


    app.goTo().GroupPage();
    Groups before = app.group().all(); // список групп до добавления новой группы
    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all(); // список групп после добавления новой группы




    // group.setId(after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()); вычисляется максимальный айди


    assertThat(after, equalTo(before));
  }

}
