package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper {
  private WebDriver wd;

  public ContactHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void initNewContactCreation() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirst_name());
    type(By.name("lastname"), contactData.getLast_name());
    type(By.name("mobile"), contactData.getPhone_number());
    type(By.name("email"), contactData.getEmail());
    selectdropdown(By.name("new_group"), contactData.getGroup_name());
  }

  private void selectdropdown(By locator, String text) {
    click(locator);
    new Select(wd.findElement(locator)).selectByVisibleText(text);
    click(locator);
  }

  private void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void submitContactForm() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  private void click(By locator) {
    wd.findElement(locator).click();
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }
}
