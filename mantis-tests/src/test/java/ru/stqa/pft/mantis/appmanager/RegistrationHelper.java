package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;


public class RegistrationHelper extends HelperBase{


    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }


    public void loginStart() {

        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), "administrator");
        type(By.name("password"), "root");
        click(By.cssSelector("input[value='Login']"));

    }


    public void usersList() {
        click(By.linkText("Manage Users"));
    }

    public void selectUser(String user) {
        type(By.name("username"), user);
        click(By.cssSelector("input[value='Manage UserData']"));
    }

    public void resetPassword() {
        click(By.cssSelector("[value = 'Reset Password']"));
    }


    public void start(String username, String email) {
        wd.get(app.getProperty("web.basUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update UserData']"));
    }
}
