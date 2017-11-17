package ru.stqa.pft.tests;





import static org.testng.Assert.assertTrue;
public class PasswordChangeTest extends TestBase {
/*
    @Test
    public void testPasswordChange() throws MessagingException, IOException {
        String serverPass = "pass001";
        String newPass = "pass003";
        List<UserData> users = app.db().readUsers();
        UserData userToReset = app.passwordReset().findUser(users);
        String username = userToReset.getUsername();
        String email = userToReset.getEmail();
        app.session().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.james().enableTelnet();
        app.james().drainEmail(username, serverPass);
        app.passwordReset().init(userToReset);
        List<MailMessage> mailMessages = app.james().waitForMail(username, serverPass, 60000);
        String confirmationLink = app.passwordReset().findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, newPass);
        assertTrue(app.newSession().login(username, newPass));
        app.session().login(username, newPass);

    }
*/
}
