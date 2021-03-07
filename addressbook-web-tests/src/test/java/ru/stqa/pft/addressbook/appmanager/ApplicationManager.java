package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final ContactHelper contactHelper = new ContactHelper();
  private NavigationHelper navigationHelper;
  private  GroupHelper groupHelper ;
  private SessionHelper sessionHelper;

  public void init() {
    contactHelper.wd = new FirefoxDriver();
    contactHelper.wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    contactHelper.wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(contactHelper.wd);
    navigationHelper = new NavigationHelper(contactHelper.wd);
    sessionHelper = new SessionHelper(contactHelper.wd);
    sessionHelper.login("admin", "secret");
  }


  public void stop() {
    doLogout();
    contactHelper.wd.quit();
  }

  private void doLogout() {
    contactHelper.wd.findElement(By.linkText("Logout")).click();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
