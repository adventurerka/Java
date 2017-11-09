package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().home();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/panda.jpg");

        app.goTo().addNewPage();
        ContactData contact = new ContactData().withFirstName("Anna").withLastName("Anna").withMiddleName("Anna").
                withNickname("Anna").withTitle("news").withAddress("Street").withHomePhone2("home").
                withMobile("+79777777777").withEmail("anna@gmail.com").withEmail2("anna2@gmail.com").
                withEmail3("anna3@gmail.com").withHomepage("anna.com").withHomePhone("+7777777").withByear("1960").
                withAyear("2020").withCompany("company").withWorkPhone("+777772").withAddress2("Street").
                withNotes("just note").withGroup("test1").withPhoto(photo);
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
