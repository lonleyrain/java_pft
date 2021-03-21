package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

/*added a check for a group to be created in application before contact creation
 because contact is waiting for at least 1 group*/

    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isGroupPresent()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));

    }

    app.getNavigationHelper().goToHomePageInHeader();
    List<ContactData> before = app.getContactHelper().getContactList(); // список контактов до добавления нового контакта
    app.getContactHelper().createContact(new ContactData("John", "Doe", "+375291111111", "dummyemail@gmail.com", "test1"));
    //app.getNavigationHelper().goToHomePageInHeader();
    List<ContactData> after = app.getContactHelper().getContactList(); // список контактов после добавления нового контакта

    Assert.assertEquals(after.size(), before.size() + 1);

  }

}
