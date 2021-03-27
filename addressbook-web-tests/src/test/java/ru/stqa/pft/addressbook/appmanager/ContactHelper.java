package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initNewContactCreation() {
    click(By.linkText("add new"));
  }

  public void modify(ContactData contact) {
    selectContactCheckboxById(contact.getId());
    initContactModification();
    fillContactForm(contact, false);
    submitContactModification();
  }


  public void delete(ContactData contact) {

    selectContactCheckboxById(contact.getId());
    deleteContactMainPage();
    closeAlert();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirst_name());
    type(By.name("lastname"), contactData.getLast_name());
    type(By.name("mobile"), contactData.getPhone_number());
    type(By.name("email"), contactData.getEmail());

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

  public int getContactCount() {

    return wd.findElements(By.name("selected[]")).size();
  }


  public Contacts all() {

    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr")); // находим все ряды таблицы
    elements.remove(0); // удаляем заголовок таблицы, то есть первый ряд, он же первый элемент списка

    for (WebElement element: elements) {

      List<WebElement> cells = element.findElements(By.tagName("td")); // создаем второй список из элементов (клеток) строки таблицы
      String first_name = cells.get(2).getText();
      String last_name = cells.get(1).getText();
      String phone_number = cells.get(5).getText();
      Integer id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirst_name(first_name).withLast_name(last_name).withPhone_number(phone_number));

    }


    return contacts;

  }

}
