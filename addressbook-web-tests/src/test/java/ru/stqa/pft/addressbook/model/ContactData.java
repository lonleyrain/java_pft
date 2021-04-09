package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("contact")
@Entity
@Table (name = "addressbook")
public class ContactData {

  @XStreamOmitField
  @Id
  @Column (name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column (name = "firstname")
  private  String first_name;

  @Expose
  @Column (name = "lastname")
  private  String last_name;

  @Expose
  @Transient
  private  String group_name;

  @Column (name = "home")
  @Type(type = "text")
  private String homePhone;

  @Expose
  @Column (name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Column (name = "work")
  @Type(type = "text")
  private String workPhone;

  @Transient
  private String allPhones;


  @Expose
  @Column (name = "email")
  @Type(type = "text")
  private  String email;

  @Column (name = "email2")
  @Type(type = "text")
  private String email2;

  @Column (name = "email3")
  @Type(type = "text")
  private String email3;

  @Transient
  private String editAddress;

  @Transient
  private String allEmails;

  @Column (name = "address")
  @Type(type = "text")
  private String mainAddress;

  @Expose
  @Column (name = "photo")
  @Type(type = "text")
  private String photo;

  /*@Expose
  private  String phone_number;*/

  /*private String email1;*/



  public File getPhoto() {
    return new File(photo);
  }
  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getMainAddress() {
    return mainAddress;
  }
  public ContactData withMainAddress(String mainAddress) {
    this.mainAddress = mainAddress;
    return this;
  }


  public String getEditAddress() {
    return editAddress;
  }
  public ContactData withEditAddress(String editAddress) {
    this.editAddress = editAddress;
    return this;
  }

  /*public String getEmail1() {
    return email1;
  }*/
  /*public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }*/

  public String getEmail2() {
    return email2;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }
  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }
  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

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

  /*public String getPhone_number() {
    return phone_number;
  }
  public ContactData withPhone_number(String phone_number) {
    this.phone_number = phone_number;
    return this;
  }*/

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
    if (last_name != null ? !last_name.equals(that.last_name) : that.last_name != null) return false;
    if (group_name != null ? !group_name.equals(that.group_name) : that.group_name != null) return false;
    if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
    return email != null ? email.equals(that.email) : that.email == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
    result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
    result = 31 * result + (group_name != null ? group_name.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }
}
