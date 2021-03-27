package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void GroupPage() {

    if (isElementPresent(By.tagName("h1"))
            &&  wd.findElement(By.tagName("h1")).getText().equals("groups")
            &&  isElementPresent(By.name("new"))) {
      return;
    }

    click(By.linkText("groups"));

  }

  public void HomePageInHeader() {

    if (isElementPresent(By.tagName("h1"))
            &&  wd.findElement(By.tagName("h1")).getText().equals("home")) {
      return;
    }

    click(By.linkText("home"));
  }
}

/*&& означаеет "и" в моменте перечисления условий
* || означает "или"
* */

//div[@id='nav']/ul[1]/li[1]/a[1]    By.id("maintable")

// if (isElementPresent(By.id("maintable"))) {
//  return;
// }
