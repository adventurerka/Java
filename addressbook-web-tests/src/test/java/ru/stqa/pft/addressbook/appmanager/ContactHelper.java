package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactCreationFrom(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[3]")).isSelected()) {
      click(By.xpath("//div[@id='content']/form/select[1]//option[3]"));
    }
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[2]")).isSelected()) {
      click(By.xpath("//div[@id='content']/form/select[2]//option[2]"));
    }
    type(By.name("byear"), contactData.getByear());
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[3]//option[3]")).isSelected()) {
      click(By.xpath("//div[@id='content']/form/select[3]//option[3]"));
    }
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[4]//option[2]")).isSelected()) {
      click(By.xpath("//div[@id='content']/form/select[4]//option[2]"));
    }
    type(By.name("ayear"), contactData.getAyear());
    if (creation) {
      new Select(wd.findElement((By.name("new_group")))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    type(By.name("address2"), contactData.getAddress2());
    type(By.name("phone2"), contactData.getPhone2());
    type(By.name("notes"), contactData.getNotes());

  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void deleteConformationContacts() {
    switchToaccept();
  }

  public void createContact(ContactData contact, boolean b) {

    fillContactCreationFrom(contact, b);
    submitContactCreation();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.contacts"));
    for (WebElement element : elements) {
      String name = element.getText();
      ContactData contact = new ContactData(name, null, null, null,
              null, null, null, null, null, null, null, null,
              null, null, null, null, null, null, null,
              null);
      contacts.add(contact);
    }
    return contacts;
  }
}
