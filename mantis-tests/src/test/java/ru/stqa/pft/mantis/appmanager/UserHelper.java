package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.tests.TestBase;

public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void selectUserById(int id) {
    click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + id + "']"));
  }

  public void resetPassword () {
    click(By.xpath("//input[@value='Reset Password']"));
  }

}
