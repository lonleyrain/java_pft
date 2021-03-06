package ru.stqa.pft.addressbook;

public class ContactData {
  private final String first_name;
  private final String last_name;
  private final String phone_number;
  private final String email;
  private final String group_name;

  public ContactData(String first_name, String last_name, String phone_number, String email, String group_name) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.phone_number = phone_number;
    this.email = email;
    this.group_name = group_name;
  }

  public String getFirst_name() {
    return first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public String getPhone_number() {
    return phone_number;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup_name() {
    return group_name;
  }
}
