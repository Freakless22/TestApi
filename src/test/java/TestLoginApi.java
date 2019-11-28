
import org.junit.Test;



public class TestLoginApi extends LoginSession {

LoginSession login = new LoginSession();
CheckSession check = new CheckSession();
DeleteSession delete = new DeleteSession();

    @Test
    public void loginCheckDeletePositiveTest(){
        login.possitiveLoginTest("Vk_User","201", "123", 200, null, null);
        check.checkLogin(login.s, "true");
        delete.deleteSession(login.s);
    }
    @Test
    public void incorrectLoginTest(){
        login.possitiveLoginTest("Vk_User1","201", "123", 401, "USER_NOT_FOUND", "User 'Vk_User1' is not registered in security database.");
    }
    @Test
    public void incorrectPasswordTest(){
        login.possitiveLoginTest("Vk_User","201", "1234", 401, "INCORRECT_PASSWORD", "Incorrect password.");
    }
    @Test
    public void incorrectDomainTest(){
        login.possitiveLoginTest("Vk_User","2011", "123", 401, "USER_NOT_FOUND", "User 'Vk_User' is not registered in security database.");
    }
    @Test
    public void badRequestTest(){
        login.badRequestTest(400);
    }
    @Test
    public void checkDeleteSession() {
        login.possitiveLoginTest("Vk_User", "201", "123", 200, null, null);
        delete.deleteSession(login.s);
        check.checkLogin(login.s, "false");
    }

}




