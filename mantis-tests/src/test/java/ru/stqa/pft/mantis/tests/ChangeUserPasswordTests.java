package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

public class ChangeUserPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test

  public void testChangeUserPassword() {

    Users before = app.db().users();




  }

  @AfterMethod(alwaysRun = true)

  public void stopMailServer() {
    app.mail().stop();
  }
}
