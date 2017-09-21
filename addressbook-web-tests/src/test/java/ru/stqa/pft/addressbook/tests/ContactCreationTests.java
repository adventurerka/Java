package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
    
    @Test
    public void testContactCreation() {
        app.gotoAddNewPage();
        app.fillContactCreationFrom(new ContactData("Anna", "Anna", "Anna", "Anna", "Anna", "news", "Street", "+7777777", "+79777777777", "+79777777777", "anna@gmail.com", "anna2@gmail.com", "anna3@gmail.com", "anna.com", "1960", "2020", "Street", "15", "just note"));
        app.submitContactCreation();
        app.gotoHome();
    }

}
