package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {


    app.goTo().GroupPage();
    Groups before = app.group().all(); // список групп до добавления новой группы
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() +1));
    Groups after = app.group().all(); // список групп после добавления новой группы


    // group.setId(after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()); вычисляется максимальный айди


    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }


  @Test
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
