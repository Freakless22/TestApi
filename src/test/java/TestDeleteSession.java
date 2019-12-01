import org.junit.Test;

public class TestDeleteSession {

    private LoginSession login = new LoginSession();
    private DeleteSession delete = new DeleteSession();

    @Test
    public void deleteActiveSession() {
        login.setUsername("vk_user");
        login.setDomain("201");
        login.setPassword("123");
        login.setStatusCode(200);
        login.baseLoginTest(login.getUsername(), login.getDomain(), login.getPassword(), login.getStatusCode(), null, null);
        delete.deleteSession(login.s, 204);
    }

    @Test
    public void deleteNotActiveSession(){
        login.setUsername("vk_user");
        login.setDomain("201");
        login.setPassword("123");
        login.setStatusCode(200);
        login.baseLoginTest(login.getUsername(), login.getDomain(), login.getPassword(), login.getStatusCode(), null, null);
        delete.deleteSession(login.s, 204);
        delete.deleteSession(login.s, 401);
    }

    @Test //TODO сделать проверку сообщения
    public void deleteWrongSession() {
        delete.setToken("someWrongToken");
        delete.deleteSession(delete.getToken(), 400);
    }

}
