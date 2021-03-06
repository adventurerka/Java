package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().home();
            app.goTo().addNewPage();
            app.contact().create((new ContactData().withFirstName("Anna").withLastName("Anna").withMiddleName("Anna").
                    withNickname("Anna").withTitle("news").withAddress("Street").withHomePhone2("+7777777").
                    withMobile("+79777777777").withEmail("anna@gmail.com").withEmail2("anna2@gmail.com").
                    withEmail3("anna3@gmail.com").withHomepage("anna.com").withHomePhone("home").withByear("1960").
                    withAyear("2020").withCompany("company").withWorkPhone("work").withAddress2("Street").
                    withNotes("just note")), true);
            app.goTo().home();
        }
    }

    @Test
    public void testContactCreation() {
        Contacts before = app.db().contacts();
        app.goTo().home();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().
                withId(modifiedContact.getId()).withFirstName("m").withLastName("Anna").withMiddleName("Anna").
                withNickname("Anna").withTitle("news").withAddress("Street").withHomePhone2("+7777777").
                withMobile("+79777777777").withEmail("anna@gmail.com").withEmail2("anna2@gmail.com").
                withEmail3("anna3@gmail.com").withHomepage("anna.com").withHomePhone("home").withByear("1960").withAyear("2020").
                withCompany("company").withWorkPhone("work").withAddress2("Street").withNotes("just note");
        app.contact().modify(contact);
        app.goTo().home();
        MatcherAssert.assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }

}
