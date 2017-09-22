package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHome();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactCreationFrom(new ContactData("a", "Anna", "Anna", "Anna", "Anna", "news", "Street", "+7777777", "+79777777777", "+79777777777", "anna@gmail.com", "anna2@gmail.com", "anna3@gmail.com", "anna.com", "1960", "2020", "Street", "15", "just note"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHome();
  }
}
