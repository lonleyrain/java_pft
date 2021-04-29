package ru.stqa.pft.rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import model.Issue;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

 @BeforeMethod
  public void checkIfIssueIsFixed() throws IOException {
    skipIfNotFixed(1);
  }

  @Test

  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = app.restHelper().getIssues();
    Issue newIssue = new Issue().withSubject("awesome subject").withDescription("awesome description");
    int issueId = app.restHelper().createIssue(newIssue);
    Set<Issue> newIssues = app.restHelper().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);

  }




}
