package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;


import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordResetTest extends TestBase {

    //@BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testPasswordReset() throws IOException, MessagingException {
        List<UserData> users = app.db().users();
        UserData selectedUser = users.iterator().next();
        String email = selectedUser.getEmail();
        String user = selectedUser.getUsername();
        String password = "newpassword";
        app.registration().loginStart();
        app.registration().usersList();
        app.registration().selectUser(user);
        app.registration().resetPassword();
        List<MailMessage> mailMessage = app.james().waitForMail(user, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessage, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    // @AfterMethod  (alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}