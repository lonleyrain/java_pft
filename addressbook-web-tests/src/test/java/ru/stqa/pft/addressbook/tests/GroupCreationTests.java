package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {


    app.goTo().GroupPage();
    List<GroupData> before = app.group().list(); // список групп до добавления новой группы
    GroupData group = new GroupData("test2", null, null);
    app.group().create(group);
    List<GroupData> after = app.group().list(); // список групп после добавления новой группы

    Assert.assertEquals(after.size(), before.size() +1);


    // group.setId(after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()); вычисляется максимальный айди
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}
