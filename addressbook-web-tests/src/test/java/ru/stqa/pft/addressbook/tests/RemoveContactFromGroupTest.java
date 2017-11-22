package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class RemoveContactFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsForContacts() {
        app.goTo().home();
        if (app.db().contacts().size() == 0) {
            app.goTo().addNewPage();
            app.contact().create(new ContactData()
                    .withFirstName("Anna").withLastName("Anna").withMiddleName("Anna").
                            withNickname("Anna").withTitle("news").withAddress("Street").withHomePhone2("+7777777").
                            withMobile("+79777777777").withEmail("anna@gmail.com").withEmail2("anna2@gmail.com").
                            withEmail3("anna3@gmail.com").withHomepage("anna.com").withHomePhone("home").withByear("1960").
                            withAyear("2020").withCompany("company").withWorkPhone("work").withAddress2("Street").
                            withNotes("just note"), true);
        }
    }

    @BeforeMethod
    public void ensurePreconditionsForGroups() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
            app.goTo().home();
            Contacts contactListBefore = app.db().contacts();
            ContactData selectContact = contactListBefore.iterator().next();
            app.contact().selectById(selectContact.getId());
            app.contact().addContactToGroup();
        } else {
            app.goTo().home();
            Contacts contactListBefore = app.db().contacts();
            ContactData selectContact = contactListBefore.iterator().next();
            app.contact().selectById(selectContact.getId());
            app.contact().addContactToGroup();
        }
    }

    @Test
    public void testRemoveContactFromGroup() {
        Contacts contactListBefore = app.db().contacts();
        Groups groupListBefore = app.db().groups();
        ContactData selectContact = contactListBefore.iterator().next();
        Groups groupSelectedForContact = selectContact.getGroups();
        GroupData selectGroup = groupListBefore.iterator().next();
        app.goTo().home();
        app.contact().selectById(selectContact.getId());
        app.contact().removeContactFromGroup();
        ContactData contactListAfter = app.db().contacts().iterator().next();
        Groups groupListAfter = contactListAfter.getGroups();
        assertThat(groupListAfter, equalTo(groupSelectedForContact.withAdded(selectGroup)));
    }

}
