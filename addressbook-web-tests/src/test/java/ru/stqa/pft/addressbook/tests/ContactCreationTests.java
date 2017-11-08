package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().home();
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        ContactData contact = new ContactData().withFirstName("Anna").withLastName("Anna").withMiddleName("Anna").
                withNickname("Anna").withTitle("news").withAddress("Street").withPhone2( "+7777777").
                withMobile("+79777777777").withEmail("anna@gmail.com").withEmail2("anna2@gmail.com").
                withEmail3("anna3@gmail.com").withHomepage("anna.com").withHome("home").withByear( "1960").withAyear("2020").
                withCompany("company").withWork("work").withAddress2("Street").withNotes("just note").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().home();
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
