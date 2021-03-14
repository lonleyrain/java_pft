package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initNewContactCreation() {
    click(By.linkText("add new"));
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
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void deleteContactInEditMode() {
    click(By.xpath("(//input[@name='update'])[3]"));
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void selectContactCheckbox() {
    //click(By.xpath("(//input[@type='checkbox'])[1]"));
    //click(By.xpath("(//input[@name='selected[]'])"));
    click(By.name("selected[]"));
  }

  public void deleteContactMainPage() {
    click(By.xpath("//input[@value='Delete']"));

  }

  public void createContact(ContactData contact) {
    initNewContactCreation();
    fillContactForm(contact,true);
    submitContactForm();
  }

  public boolean isContactPresent() {
    //return isElementPresent(By.xpath("(//input[@name='selected[]'])"));
    return isElementPresent(By.name("selected[]"));
  }
}
