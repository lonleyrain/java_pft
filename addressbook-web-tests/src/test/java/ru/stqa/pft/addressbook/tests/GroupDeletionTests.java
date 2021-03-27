package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {

    app.goTo().GroupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }

  }


  @Test
  public void testGroupDeletion() throws Exception {


    Set<GroupData> before = app.group().all(); // список групп до удаления группы
    GroupData deletedGroup = before.iterator().next(); // обращаемся к множеству через итератор и используем метод next чтобы вернуть первый попавшийся элемент множества
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all(); // количество групп после удаления новой группы
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedGroup);
    Assert.assertEquals(before, after);

  }

}
