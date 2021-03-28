package ru.stqa.pft.addressbook.model;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  private  String first_name;
  private  String last_name;
  private  String phone_number;
  private  String email;
  private  String group_name;
  private  String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String allPhones;



  public String getAllPhones() {
    return allPhones;
  }
  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getHomePhone() {
    return homePhone;
  }
  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }
  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }
  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public int getId() {
    return id;
  }
  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public String getFirst_name() {
    return first_name;
  }
  public ContactData withFirst_name(String first_name) {
    this.first_name = first_name;
    return this;
  }

  public String getLast_name() {
    return last_name;
  }
  public ContactData withLast_name(String last_name) {
    this.last_name = last_name;
    return this;
  }

  public String getPhone_number() {
    return phone_number;
  }
  public ContactData withPhone_number(String phone_number) {
    this.phone_number = phone_number;
    return this;
  }

  public String getEmail() {
    return email;
  }
  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public String getGroup_name() {
    return group_name;
 }
  public ContactData withGroup_name(String group_name) {
    this.group_name = group_name;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", first_name='" + first_name + '\'' +
            ", last_name='" + last_name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null) return false;
    return last_name != null ? last_name.equals(that.last_name) : that.last_name == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
    result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
    return result;
  }

}
