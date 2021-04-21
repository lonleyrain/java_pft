package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUserPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test

  public void testChangeUserPassword() throws MessagingException, IOException {

    Users before = app.db().users();
    before.removeIf(user -> user.getUsername().equals("administrator"));
    UserData userToChangePasswordFor = before.iterator().next();

    String email = userToChangePasswordFor.getEmail();
    String user = userToChangePasswordFor.getUsername();
    String password = "awesomePassword";

    // 1. login as administrator

    app.sessionHelper().appLogin("administrator", "root");




    // 2. click manage -> manage users tab
    // 3. select a user from the list by id (received from the selected user to be modifed)
    // 4. click reset password button
    // 5. click users drop-down -> logout








    long now = System.currentTimeMillis();

    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);

    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));

  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression
            .regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)

  public void stopMailServer() {
    app.mail().stop();
  }
}
