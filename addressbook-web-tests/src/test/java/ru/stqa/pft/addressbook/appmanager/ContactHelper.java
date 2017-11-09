package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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

    public void submitCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void submitModification() {
        click(By.name("update"));
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void deleteConformationContacts() {
        switchToaccept();
    }

    public void create(ContactData contact, boolean b) {

        fillContactCreationFrom(contact, b);
        submitCreation();
        contactCache = null;
    }
    public void modify(ContactData contact) {
        initModificationById(contact.getId());
        fillContactCreationFrom(contact, false);
        submitModification();
        contactCache = null;
    }
    public void delete(List<ContactData> before) {
        select(before.size());
        deleteSelectedContacts();
        deleteConformationContacts();
    }
    public void delete(ContactData contact) {
        selectById(contact.getId());
        deleteSelectedContacts();
        deleteConformationContacts();
        contactCache = null;
    }
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.cssSelector("td"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }
    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));

        for (WebElement element : elements) {
            List<WebElement> el = element.findElements(By.cssSelector("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = el.get(1).getText();
            String firstName = el.get(2).getText();
            ContactData contact = new ContactData().withId(id).withLastName(lastName).withFirstName(firstName);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }
public void initModificationById(int id){
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../../."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
}
    public ContactData infoFromEditForm(ContactData contact) {
        initModificationById(contact.getId());
        return null;
    }
}
