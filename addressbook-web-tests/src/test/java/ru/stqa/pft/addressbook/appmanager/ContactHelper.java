package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initNewContactCreation() {
    click(By.linkText("add new"));
  }

  public void modify(int index, ContactData contact) {
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactModification();
  }

  public void delete(int index) {
    selectContactCheckbox(index);
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

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
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

  public List<ContactData> list() {

    List<ContactData> contacts = new ArrayList<ContactData>();
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


/*List<WebElement> elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr"));
    for (WebElement element : elements) {*/

//String first_name = wd.findElement(By.xpath("//tr[2]/td[3]")).getText();
//String first_name = wd.findElement(By.xpath("//th[@class='sortable fd-column-2']")).getText();
//String last_name = wd.findElement(By.xpath("//tr[2]/td[2]")).getText();
//String last_name = wd.findElement(By.xpath("//th[@class='sortable fd-column-1']")).getText();
//String phone_number = wd.findElement(By.xpath("//tr[2]/td[5]")).getText();
//String phone_number = wd.findElement(By.xpath("//th[@class='sortable fd-column-5']")).getText();

//List<WebElement> elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr"));