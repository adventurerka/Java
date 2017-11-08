package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase{

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
    public void testContactPhones (){
        app.goTo().home();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }
}
