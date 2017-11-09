package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }
/*
    public LegalEntity withName(String name) {
        this.name = name;
        return this;
    }
    public LegalEntity withName() {
        this.name = "имя";
        return this;
    }
    public LegalEntity withOgrn(String ogrn) {
        this.ogrn = ogrn;
        return this;
    }
    public LegalEntity withOgrn() {
        this.ogrn = "";
        return this;
    }
    public LegalEntity withInn(String inn) {
        this.inn = inn;
        return this;
    }

    public LegalEntity withKpp(String kpp) {
        this.kpp = kpp;
        return this;
    }

    public LegalEntity withPosition(String position) {
        this.position = position;
        return this;
    }

    public LegalEntity withDivision(String division) {
        this.division = division;
        return this;
    }
    */
}
