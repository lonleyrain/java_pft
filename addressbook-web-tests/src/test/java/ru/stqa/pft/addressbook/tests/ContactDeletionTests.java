package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {


  @BeforeMethod

  public void ensurePreconditions() {

    /*added a check for a group to be created in application before contact modification
    because contact is waiting for at least 1 group to be present in app*/

    app.goTo().GroupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData("test1", null, null));

    }

    app.goTo().HomePageInHeader();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData("First name", "Last name", "+375290000000", "dummyemail@gmail.com", "test1"));
    }

  }


  @Test (enabled = true)

  public void testContactDeletion() throws Exception {


    app.goTo().HomePageInHeader();

    List<ContactData> before = app.contact().list(); // список контактов до удаления  контакта
    int index = before.size() - 1;

    app.contact().delete(index);

    app.goTo().HomePageInHeader();

    List<ContactData> after = app.contact().list(); // список контактов после удаления  контакта

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);

    Assert.assertEquals(before, after);


  }


}
