package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().home();

        if (app.contact().all().size() == 0) {
            app.goTo().addNewPage();
            app.contact().create((new ContactData().withFirstName("Anna").withLastName("Anna").withMiddleName("Anna").
                    withNickname("Anna").withTitle("news").withAddress("Street").withPhone2( "+7777777").
                    withMobile("+79777777777").withEmail("anna@gmail.com").withEmail2("anna2@gmail.com").
                    withEmail3("anna3@gmail.com").withHomepage("anna.com").withHome("home").withByear( "1960").
                    withAyear("2020").withCompany("company").withWork("work").withAddress2("Street").
                    withNotes("just note").withGroup("test1")), true);
            app.goTo().home();
        }
    }

    @Test
    public void testContactCreation() {

        Contacts before = app.contact().all();
        app.goTo().home();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().
                withId(modifiedContact.getId()).withFirstName( "m").withLastName("Anna").withMiddleName("Anna").
                withNickname("Anna").withTitle("news").withAddress("Street").withPhone2( "+7777777").
                withMobile("+79777777777").withEmail("anna@gmail.com").withEmail2("anna2@gmail.com").
                withEmail3("anna3@gmail.com").withHomepage("anna.com").withHome("home").withByear( "1960").withAyear("2020").
                withCompany("company").withWork("work").withAddress2("Street").withNotes("just note").withGroup("test1");
        app.contact().modify(contact);
        app.goTo().home();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
