package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test (enabled = false)

  public void testContactModification() throws Exception {


    /*added a check for a group to be created in application before contact modification
    because contact is waiting for at least 1 group to be present in app*/

    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isGroupPresent()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));

    }

    if (! app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData("First name", "Last name", "+375290000000", "dummyemail@gmail.com", "test1"));
    }

    app.getNavigationHelper().goToHomePageInHeader();

    List<ContactData> before = app.getContactHelper().getContactList(); // список контактов до изменения контакта

    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(),"First name", "Last name", "+375290000000", "dummyemail@gmail.com", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomePageInHeader();

    List<ContactData> after = app.getContactHelper().getContactList(); // список контактов после изменения контакта

    Assert.assertEquals(after.size(), before.size());


    before.remove(before.size() - 1); // удаляем оставшийся неизмененным объект из списка
    before.add(contact); // добавляем в список объект измененного контакта, чтобы списки актуализировались

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);


  }


}
