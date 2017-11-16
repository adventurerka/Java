package ru.stqa.pft.mantis.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().home();

        if (app.contact().all().size() == 0) {
            app.goTo().addNewPage();
            app.contact().create((new ContactData().withFirstName("Anna").withLastName("Anna").withMiddleName("Anna").
                    withNickname("Anna").withTitle("news").withAddress("Street").withHomePhone2("+7777771").
                    withMobile("+79777777777").withEmail("anna@gmail.com").withEmail2("anna2@gmail.com").
                    withEmail3("anna3@gmail.com").withHomepage("anna.com").withHomePhone("home").withByear("1960").
                    withAyear("2020").withCompany("company").withWorkPhone("+7777772").withAddress2("Street").
                    withNotes("just note")), true);
            app.goTo().home();
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().home();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone(), contact.getHomePhone2())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
