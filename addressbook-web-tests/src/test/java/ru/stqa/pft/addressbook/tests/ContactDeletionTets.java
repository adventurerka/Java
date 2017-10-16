package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTets extends TestBase {
  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHome();
    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().createContact(new ContactData("Anna", "Anna",
              "Anna", "Anna", "Anna", "news", "Street", "+7777777",
              "+79777777777", "+79777777777", "anna@gmail.com", "anna2@gmail.com",
              "anna3@gmail.com", "anna.com", "1960", "2020", "test1",
              "Street", "15", "just note"), true);
      app.getNavigationHelper().gotoHome();
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().deleteConformationContacts();
    app.getNavigationHelper().gotoHome();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before-1);
  }
}
