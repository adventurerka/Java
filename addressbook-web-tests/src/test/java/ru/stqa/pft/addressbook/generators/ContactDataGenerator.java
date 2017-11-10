package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-d", description = "Data format")
    public String format;

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
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s \n", contact.getFirstname(),
                    contact.getLastname(), contact.getMiddlename(), contact.getNickname(), contact.getTitle(),
                    contact.getAddress(), contact.getHomePhone2(), contact.getMobile(), contact.getEmail(),
                    contact.getEmail2(), contact.getEmail3(), contact.getHomepage(), contact.getHomePhone(),
                    contact.getByear(), contact.getAyear(), contact.getCompany(), contact.getWorkPhone(),
                    contact.getAddress2(), contact.getNotes(), contact.getGroup()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("Anna %s", i)).
                    withLastName(String.format("Anna %s", i)).withMiddleName(String.format("Anna %s", i)).
                    withNickname(String.format("Anna %s", i)).withTitle(String.format("news %s", i)).
                    withAddress(String.format("Street %s", i)).withHomePhone2(String.format("home %s", i)).
                    withMobile(String.format("+79777777777 %s", i)).withEmail(String.format("anna@gmail.com %s", i)).
                    withEmail2(String.format("anna2@gmail.com %s", i)).withEmail3(String.format("anna3@gmail.com %s", i)).
                    withHomepage(String.format("anna.com %s", i)).withHomePhone(String.format("+7777777 %s", i)).
                    withByear(String.format("1960 %s", i)).withAyear(String.format("2020 %s", i)).
                    withCompany(String.format("company %s", i)).withWorkPhone(String.format("+777772 %s", i)).
                    withAddress2(String.format("Street %s", i)).withNotes(String.format("just note %s", i)).
                    withGroup(String.format("test %s", i)));
        }
        return contacts;
    }
}
