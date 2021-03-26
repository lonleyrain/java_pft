package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {

    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isGroupPresent()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }

  }


  @Test
  public void testGroupDeletion() throws Exception {

   
    List<GroupData> before = app.getGroupHelper().getGroupList(); // список групп до удаления группы
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList(); // количество групп после удаления новой группы

    Assert.assertEquals(after.size(), before.size() -1);


    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  }

}
