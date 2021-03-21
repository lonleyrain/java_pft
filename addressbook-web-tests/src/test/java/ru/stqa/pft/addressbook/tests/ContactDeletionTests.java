package ru.stqa.pft.addressbook.tests;

import org.apache.tools.ant.taskdefs.Sleep;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test

  public void testContactDeletion() throws Exception {

    app.getNavigationHelper().goToHomePageInHeader();
    if (! app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData("First name", "Last name", "+375290000000", "dummyemail@gmail.com", "test1"));
    }

    List<ContactData> before = app.getContactHelper().getContactList(); // список контактов до удаления  контакта
    app.getContactHelper().selectContactCheckbox(before.size() - 1);
    app.getContactHelper().deleteContactMainPage();
    app.getContactHelper().closeAlert();

    app.getNavigationHelper().goToHomePageInHeader();

    List<ContactData> after = app.getContactHelper().getContactList(); // список контактов после удаления  контакта

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);

    for (int i = 0; i < after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i));
    }



  }

}
