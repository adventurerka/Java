package ru.stqa.pft.mantis.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.addressbook.model.ContactData;
import ru.stqa.pft.mantis.addressbook.model.Contacts;
import ru.stqa.pft.mantis.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsXml() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
        return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validContactsJson() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
        }.getType());
        return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactsXml")
    public void testContactCreation(ContactData contact) {
        app.goTo().home();
        Groups groups = app.db().groups();
        ContactData newContact = contact.inGroup(groups.iterator().next());
        Contacts before = app.db().contacts();
        app.goTo().addNewPage();
        app.contact().create(newContact, true);
        app.goTo().home();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        verifyContactListInUI();
    }

    @Test(enabled = false)
    public void testContactCreationOld() {
        Groups groups = app.db().groups();
        File photo = new File("src/test/resources/panda.jpg");
        ContactData contact = new ContactData().withFirstName("Anna").withLastName("Anna").withMiddleName("Anna").
                withNickname("Anna").withTitle("news").withAddress("Street").withHomePhone2("home").
                withMobile("+79777777777").withEmail("anna@gmail.com").withEmail2("anna2@gmail.com").
                withEmail3("anna3@gmail.com").withHomepage("anna.com").withHomePhone("+7777777").withByear("1960").
                withAyear("2020").withCompany("company").withWorkPhone("+777772").withAddress2("Street").
                withNotes("just note").withPhoto(photo).inGroup(groups.iterator().next());
        app.goTo().home();
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        app.contact().create(contact, true);
        app.goTo().home();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/panda.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}
