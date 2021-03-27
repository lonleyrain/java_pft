package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {


    app.goTo().GroupPage();
    Set<GroupData> before = app.group().all(); // список групп до добавления новой группы
    GroupData group = new GroupData().withName("test2");
    app.group().create(group);
    Set<GroupData> after = app.group().all(); // список групп после добавления новой группы

    Assert.assertEquals(after.size(), before.size() +1);


    // group.setId(after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()); вычисляется максимальный айди

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()); //здесь должен вычисляться максимальный индентификатор
    before.add(group);


    Assert.assertEquals(before, after);
  }

}
