package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter (names = "-c", description = "Contacts count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {

    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s\n",
              contact.getFirst_name(),
              contact.getLast_name(),
              contact.getPhone_number(),
              contact.getEmail(),
              contact.getGroup_name()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withFirst_name(String.format("FirstNameTest %s", i))
              .withLast_name(String.format("LastNameTest %s", i))
              .withPhone_number(String.format("37529000000%s", i))
              .withEmail(String.format("test_email@test %s", i))
              .withGroup_name(String.format("test%s",i)));
    }
    return contacts;
  }
}
