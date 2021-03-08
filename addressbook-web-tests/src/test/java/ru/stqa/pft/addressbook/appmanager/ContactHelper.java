package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initNewContactCreation() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirst_name());
    type(By.name("lastname"), contactData.getLast_name());
    type(By.name("mobile"), contactData.getPhone_number());
    type(By.name("email"), contactData.getEmail());
    //selectdropdown(By.name("new_group"), contactData.getGroup_name());
  }

  public void submitContactForm() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
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
    click(By.xpath("(//input[@type='checkbox'])[1]"));
  }

  public void deleteContactMainPage() {
    click(By.xpath("//input[@value='Delete']"));

  }
}
