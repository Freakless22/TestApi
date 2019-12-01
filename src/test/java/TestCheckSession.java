import org.junit.Test;

public class TestCheckSession {

    private LoginSession login = new LoginSession();
    private CheckSession check = new CheckSession();
    private DeleteSession delete = new DeleteSession();

    @Test
    public void checkActiveSession() {
        login.setUsername("vk_user");
        login.setDomain("201");
        login.setPassword("123");
        login.setStatusCode(200);
        login.baseLoginTest(login.getUsername(), login.getDomain(), login.getPassword(), login.getStatusCode(), null, null);
        check.checkLogin(login.s, "true", 200);
    }



    @Test
    public void checkDeleteSession(){
        login.setUsername("vk_User");
        login.setDomain("201");
        login.setPassword("123");
        login.setStatusCode(200);
        login.baseLoginTest(login.getUsername(), login.getDomain(), login.getPassword(), login.getStatusCode(), null, null);
        delete.deleteSession(login.s, 204);
        check.checkLogin(login.s, "false", 200);
    }

    @Test //TODO сделать проверку на isActive
    public void checkWrongSession(){
        check.checkLogin("e2d1d6e6-369d-4edd-8933-qq", null, 400);
    }

}