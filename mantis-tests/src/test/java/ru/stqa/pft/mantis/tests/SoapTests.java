package ru.stqa.pft.mantis.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class SoapTests extends TestBase {

  @BeforeMethod
  public void checkIfIssueIsFixed() throws MalformedURLException, ServiceException, RemoteException {
    skipIfNotFixed(0000001);
  }

  @Test

  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {

    Set<Project> projects = app.soapHelper().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test

  public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soapHelper().getProjects();

    Issue issue = new Issue()
            .withSummary("Test issue")
            .withDescription("test issue description")
            .withProject(projects.iterator().next());

    Issue created_issue = app.soapHelper().addIssue(issue);
    assertEquals(issue.getSummary(), created_issue.getSummary());

  }

}
