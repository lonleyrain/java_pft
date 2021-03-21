package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String id;
  private final String first_name;
  private final String last_name;
  private final String phone_number;
  private final String email;
  private final String group_name;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null) return false;
    return last_name != null ? last_name.equals(that.last_name) : that.last_name == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
    result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", first_name='" + first_name + '\'' +
            ", last_name='" + last_name + '\'' +
            '}';
  }

  public String getId() {
    return id;
  }

  public ContactData(String first_name, String last_name, String phone_number, String email, String group_name) {
    this.id = null;
    this.first_name = first_name;
    this.last_name = last_name;
    this.phone_number = phone_number;
    this.email = email;
    this.group_name = group_name;

  }


  public ContactData(String id, String first_name, String last_name, String phone_number, String email, String group_name) {
    this.id = id;
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
