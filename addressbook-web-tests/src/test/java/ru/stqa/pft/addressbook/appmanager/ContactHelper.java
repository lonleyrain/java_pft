package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.util.List;


public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initNewContactCreation() {
    click(By.linkText("add new"));
  }

  public void modify(ContactData contact) {
    selectContactCheckboxById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
  }

  public void delete(ContactData contact) {

    selectContactCheckboxById(contact.getId());
    deleteContactMainPage();
    closeAlert();
    contactCache = null;
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirst_name());
    type(By.name("lastname"), contactData.getLast_name());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup_name());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactForm() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void initContactModification() {
    wd.findElement(By.xpath("//img[@alt='Edit']")).click();
  }

  public void deleteContactInEditMode() {
    click(By.xpath("(//input[@name='update'])[3]"));
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void selectContactCheckbox(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactCheckboxById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteContactMainPage() {
    click(By.xpath("//input[@value='Delete']"));

  }

  public void create(ContactData contact) {
    initNewContactCreation();
    fillContactForm(contact, true);
    submitContactForm();
    contactCache = null;
    goToHomePageInConfirmation();

  }

  public void goToHomePageInConfirmation() {

    //wd.findElement(By.linkText("home page")).click();

    click(By.linkText("home page"));
  }

  public boolean isContactPresent() {
    //return isElementPresent(By.xpath("(//input[@name='selected[]'])"));
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {

    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;


  public Contacts all() {

    if (contactCache != null) {
      return new Contacts (contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr")); // находим все ряды таблицы
    elements.remove(0); // удаляем заголовок таблицы, то есть первый ряд, он же первый элемент списка

    for (WebElement element: elements) {

      List<WebElement> cells = element.findElements(By.tagName("td")); // создаем второй список из элементов (клеток) строки таблицы
      Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String first_name = cells.get(2).getText();
      String last_name = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String mainAddress = cells.get(3).getText();

      contactCache.add(new ContactData()
              .withId(id)
              .withFirst_name(first_name)
              .withLast_name(last_name)
              .withAllPhones(allPhones)
              .withAllEmails(allEmails)
              .withMainAddress(mainAddress));

    }


    return new Contacts(contactCache);

  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String editAddress = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId())
            .withFirst_name(firstname)
            .withLast_name(lastname)
            .withHomePhone(home)
            .withMobilePhone(mobile)
            .withWorkPhone(work)
            .withEmail(email1)
            .withEmail2(email2)
            .withEmail3(email3)
            .withEditAddress(editAddress);

  }

  private void initContactModificationById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }


}
