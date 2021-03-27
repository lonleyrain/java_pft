package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {

    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }

  }

  @Test

  public void testGroupModification() {


    Set<GroupData> before = app.group().all(); // список групп до изменения группы
    GroupData modifiedGroup = before.iterator().next(); // обращаемся к множеству через итератор и используем метод next чтобы вернуть первый попавшийся элемент множества

    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("test1")
            .withHeader("test2")
            .withFooter("test3");

    app.group().modify(group);

    Set<GroupData> after = app.group().all(); // список групп после изменения группы

    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(group);



    Assert.assertEquals(before, after);

  }


}
