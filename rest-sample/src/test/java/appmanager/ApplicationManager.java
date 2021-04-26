package appmanager;

public class ApplicationManager {

  public RestHelper restHelper;


  public RestHelper restHelper() {
    if (restHelper == null) {
      restHelper = new RestHelper(this);
    }
    return restHelper;
  }
}
